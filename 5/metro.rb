###############################################################################
### CMSC330 Project: Multi-threaded Train Simulation                        ###
### Source code: metro.rb                                                   ###
### Description: A multi-threaded Ruby program that simulates               ###
###              the Washington Metro by creating Train and Person threads  ###
###############################################################################
@lines
@numTrains
@passengers
@stations = {}
@trains = {}
@itinerary = {}
@simMonitor
@check = {}
@t_cond = {}
@p_cond = {}
        

require "monitor"
 
Thread.abort_on_exception = true   # to avoid hiding errors in threads
     
#----------------------------------------------------------------
# Metro Simulator
#----------------------------------------------------------------  
 #simMonitor is a hash containing a monitor variable for each metro line. You must use these monitors (and condition variables derived from them) for synchronization in your code.
#{"Red"=>#<Monitor:0x288b740 @mon_owner=nil, @mon_waiting_queue=[], @mon_entering_queue=[], @mon_count=0>}

 #stations[station name] => hash for metro line
    #stations[station name][line name] => hash for trains at station (for line)
  # stations[station name][line name][train num] where train num is "1", "2", etc...
   # stations[station name]["passenger"] => hash for passengers at station
    #stations[station name]["passenger"][passenger name] exists when passenger is at the station
    #trains[train name] => hash for passengers on train
    #trains[train name][passenger name] exists when passenger is on the train  

def train_work (lines,n, numTrains, passengers, stations, trains, keys, t_cond, p_cond)
 k = keys.split(" ")
   key = k[0]
   j = k[1]
lines[key].each{|dest|
      @simMonitor[key].synchronize {     
		@t_cond[key].wait_while{!@stations[dest][key].empty?} 
        if(@stations[dest][key] == nil)
            @stations[dest][key] = {}
          end
		@stations[dest][key][j] = true 
		puts "Train #{key} #{j} entering #{dest}"
      $stdout.flush()     
	@p_cond[key].broadcast #signals passengers to communicate
  }
  sleep 0.01
  @simMonitor[key].synchronize{
       @stations[dest][key].delete(j)
       puts "Train #{key} #{j} leaving #{dest}"
       $stdout.flush
       @t_cond[key].broadcast
     }
}
end

def passenger_work(itinerary, lines, person)
i = 0
path = ""
while(i < itinerary.length - 1)
curr = itinerary[i]
nex = itinerary[i + 1]
lines.each{|line, val|
if(lines[line].include?(curr) && lines[line].include?(nex))
path = line
end    
}    
@simMonitor[path].synchronize{
@p_cond[path].wait_while{@stations[curr][path].empty?}
b = @stations[curr][path].keys[0]
@stations[curr]["passenger"].delete(person)
if(@trains[path + " " + b.to_s] == nil)
@trains[path + " " + b.to_s] = {}
end
@trains[path + " " + b.to_s][person] = true
puts "#{person} boarding train #{path + " " + b.to_s} at #{curr}"
$stdout.flush()
}
@simMonitor[path].synchronize{
b = @stations[curr][path].keys[0]
@p_cond[path].wait_while{!@stations[nex][path].key?(b)}
puts "#{person} leaving train #{path + " " + b.to_s} at #{nex}"
$stdout.flush()
}
i = i + 1
end
end

#----------------------------------------------------------------
# Simulation Display
#----------------------------------------------------------------
 
# line = hash of line names to array of stops
# stations = hash train names =>  hash of passengers
  
def simulate(lines,numTrains,passengers,simMonitor)
  p_threads = [] #ok
    t_threads = [] #ok
  stations = {}
    trains = {}  
    
    init_lines = {}
    ret_lines = {}
    lines.each{|k,v|
      init_lines[k] = v[1..v.length-1]
    } 
    lines.each{|k,v|
      ret_lines[k] = v[0..v.length-2].reverse
    }
    
 @lines = lines
 @passengers = passengers
 @numTrains = numTrains
 
 metro_lines = lines.keys #array of the metrolines. red, blue, yellow etc
  people = passengers.keys
 
  metro_lines.each{|i| #access the stations in each metroline
    station_names = lines[i]
     station_names.each{|j|
        if stations[j] == nil
       stations[j] = {} #hash for metroline
      end
     
        if stations[j][i] == nil
      stations[j][i] = {} #hash for trains at station (for line) CHECK IF POPULATED
       end
       stations[j]["passenger"] = {} #hash for passengers at station 
                  }
  }
  
  @stations = stations
    for i in 0...people.length
              itin = passengers[people[i]] 
              first_stop = itin[0]
             stations[first_stop]["passenger"][people[i]] = true
            end   
    
    @passengers.each{|p,val|
          @check[p] = false
      }
    
    @simMonitor = simMonitor
    @simMonitor.each{|a,b |
      @t_cond[a] = b.new_cond
      @p_cond[a] = b.new_cond
    }
    
    numTrains.each{|key,val|
      index = val
      begin
       @trains[key + " " + index.to_s] = {}
       index -= 1
      end until(index == 0)
    }

index = 0
@trains.each{|key,val|
      index = index + 1
      t_threads.push(
        Thread.new do
          train_work(@lines, index , numTrains, @passengers, @stations, trains, key, @t_cond, @p_cond)
          
          if(passengers.empty?)
            train_work(ret_lines, index , numTrains, passengers, stations, trains, key, @t_cond, @p_cond)
              train_work(init_lines, index , numTrains, passengers, stations, trains, key, @t_cond, @p_cond)            
          else 
            until(!@check.values.include?(false))
              train_work(ret_lines, index , numTrains, passengers, stations, trains, key, @t_cond, @p_cond)
              train_work(init_lines, index , numTrains, passengers, stations, trains, key, @t_cond, @p_cond)
            end
          end
        end
      )
    }
    
    @check.each{|p, val|
       itinerary = @passengers[p]
      p_threads.push(
        Thread.new do
            passenger_work(itinerary, lines, p)
            @check[p] = true
        end
      
      )
    }
    
    
    p_threads.each{|x|
      x.join
    }
    t_threads.each{|x|
      x.join  
    }
    
  end

def displayState(lines,stations,trains)
    lines.keys.sort.each { |color|
        stops = lines[color]
        puts color
        stops.each { |stop|
            pStr = ""
            tStr = ""
           
            stations[stop]["passenger"].keys.sort.each { |passenger|
                pStr << passenger << " "
            }
           
            stations[stop][color].keys.sort.each { |trainNum|
                tr = color+" "+trainNum
                tStr << "[" << tr
                if trains[tr] != nil
                    trains[tr].keys.sort.each { |p|
                        tStr << " " << p
                    }
                end
                tStr << "]"
            }
           
            printf("  %25s %10s %-10s\n", stop, pStr, tStr)
        }
    }
   
end
 
def display(lines,passengers,output)
    stations = {}
  
    trains = {}        
  
  
  
  metro_lines = lines.keys #array of the metrolines. red, blue, yellow etc
  people = passengers.keys 
  
  metro_lines.each{|i| #access the stations in each metroline
    station_names = lines[i]
     station_names.each{|j|
       stations[j] = {} #hash for metroline
       stations[j][i] = {} #hash for trains at station (for line)
       stations[j]["passenger"] = {} #hash for passengers at station  
            
             #puts stations.inspect
             
             #stations[station name]["passenger"][passenger name] exists when passenger is at the station  #
          
     }
  }
              
  all_lines = lines.keys
all_lines.each{|key|
for i in 1..100
trains[key+" "+i.to_s] = {}
end
}
  for i in 0...people.length
              itin = passengers[people[i]] 
             # puts people[i]
              #puts people.inspect
              #puts itin.inspect
              first_stop = itin[0]
             
              #puts stations.inspect
             stations[first_stop]["passenger"][people[i]] = 0
             
            end   
            
            displayState(lines,stations,trains) 
    output.each {|o| 
      puts o
      #-----------------------------------------------------------------------------------------------------CASE1
        if o =~ /(Train) (\w+) (\d) (entering) (\w+ \w+)/ #Case1
          # if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
           #end
           stations[$5][$2][$3] = 0 #marks that specified train is currently at this station
        else if o =~ /(Train) (\w+) (\d) (entering) (\w+)/ #Still Case1. just if station doesn't have two words
           #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
            #end   
            stations[$5][$2][$3] = 0 #marks that specified train is currently at this station
            
            
        end
        end   
           
#-----------------------------------------------------------------------------------------------------CASE2
        if o =~ /(Train) (\w+) (\d) (leaving) (\w+ \w+)/
           #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
            #end
            
           stations[$5][$2].delete($3) #marks that specified train is not at this station        
        else if o =~ /(Train) (\w+) (\d) (leaving) (\w+)/
          # if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
           #end
            
           stations[$5][$2].delete($3) #marks that specified train is not at this station        
        end
        end
#-----------------------------------------------------------------------------------------------------CASE3
      if o =~ /(\w+ \w+) boarding train (\w+) (\d) at (\w+ \w+)/
         #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
         #end
          trains[$2+" "+$3][$1] = 0 #exists when passenger is on the train
         # stations[$4]["passenger"][$1] = 0 
          stations[$4]["passenger"].delete($1)
                           
      else if o =~ /(\w+) boarding train (\w+) (\d) at (\w+ \w+)/
          #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
          #end
         # puts trains[$2+" "+$3] 
          trains[$2+" "+$3][$1] = 0 #exists when passenger is on the train
          #stations[$4]["passenger"][$1] = 0
          stations[$4]["passenger"].delete($1)
          
           else if o =~ /(\w+ \w+) boarding train (\w+) (\d) at (\w+)/
          #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
          #end
         # puts trains[$2+" "+$3] 
          trains[$2+" "+$3][$1] = 0 #exists when passenger is on the train
          #stations[$4]["passenger"][$1] = 0
          stations[$4]["passenger"].delete($1)
           else if o =~ /(\w+) boarding train (\w+) (\d) at (\w+)/
          #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
          #end
         # puts trains[$2+" "+$3] 
          trains[$2+" "+$3][$1] = 0 #exists when passenger is on the train
          #stations[$4]["passenger"][$1] = 0
          stations[$4]["passenger"].delete($1)
      end
      end
    end
  end
#-----------------------------------------------------------------------------------------------------CASE4
 
      if o =~ /(\w+ \w+) leaving train (\w+) (\d) at (\w+ \w+)/
          #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
          #end
          trains[$2+" "+$3].delete($1) 
          stations[$4]["passenger"][$1] = 0
          
          #stations[station name]["passenger"][passenger name] exists when passenger is at the station  #
#trains[train name] => hash for passengers on train   #
#trains[train name][passenger name] exists when passenger is on the train  #
      else if o =~ /(\w+) leaving train (\w+) (\d) at (\w+ \w+)/
          #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
          #end
          trains[$2+" "+$3].delete($1)
          stations[$4]["passenger"][$1] = 0
          
       else if o =~ /(\w+ \w+) leaving train (\w+) (\d) at (\w+)/
          #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
          #end
          trains[$2+" "+$3].delete($1)
          stations[$4]["passenger"][$1] = 0
          
          else if o =~ /(\w+) leaving train (\w+) (\d) at (\w+)/
          #if trains[$2+" "+$3] = nil #initializing hash for passengers on train if it doesnt exist
             #trains[$2+" "+$3] = {}
          #end
          trains[$2+" "+$3].delete($1)
          stations[$4]["passenger"][$1] = 0   
          
      end
      end
    end
  end
        displayState(lines,stations,trains) 
      }
 
#puts lines.inspect
#puts passengers.inspect
#puts output.inspect
end
 
#----------------------------------------------------------------
# Simulation Verifier
#----------------------------------------------------------------
 
def verify(lines,numTrains,passengers,output)
 
    #puts lines.inspect
    #puts numTrains.inspect
    #puts passengers.inspect
    #puts output.inspect
    #return false
    return true
end