# CMSC 330 / summer 2012 / Project 1
# Student: Victor Oyefusi

#read in file
file = File.new(ARGV[1], "r")
lines = file.readlines

#validation code
if ARGV[0] == "validate"
boolean = true
for i in 0...lines.length
if lines[i] =~ /([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\s(\-)\s(\-|\w+|\d+|\_)\s\[(\d{1,2}\/\w{1,3}\/\d{4}\:\d{2}\:\d{2}\:\d{2}\s\-\d{4})\]\s(\".+\")\s(\d{1,8})\s(\d{1,8})/

else
boolean = false
break
end
end 
 
for i in 0...lines.length
 if lines[i] =~ /(\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})\s(\-)\s(\-|\w+|\d+|\_)\s\[(\d{1,2}\/\w{1,3}\/\d{4}\:\d{2}\:\d{2}\:\d{2}\s\-\d{4})\]\s(\".+\")\s(\d{1,8})\s(\d{1,8})/
ip = $1
hyphen = $2
name = $3
date = $4
request = $5
status = $6
bytes = $7
request =~ /"(.+)"/

if request =~ /([^\\]\"|\\$)/ 
 else
   boolean = false   
end

end
end


if boolean == false
  print "no" + "\n"
else
  print "yes" + "\n"
 end
end
#end validation code

#begin byte code
if ARGV[0] == "bytes"
byte_count = 0
for i in 0...lines.length
 if lines[i] =~ /(\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})\s(\-)\s(\-|\w+|\d+|\_)\s\[(\d{1,2}\/\w{1,3}\/\d{4}\:\d{2}\:\d{2}\:\d{2}\s\-\d{4})\]\s(\".+\")\s(\d{1,8})\s(\d{1,8})/
ip = $1
hyphen = $2
name = $3
date = $4
request = $5
status = $6
bytes = $7
byte_count += bytes.to_i
end
end
if byte_count < 1024*1024
#print in KB
kb = byte_count/1024
print kb.to_i
print  " KB\n"
elsif (byte_count >= 1024*1024) and (byte_count < 1024*1024*1024)
#print in MB
mb = byte_count/(1024*1024)
print mb.to_i
print  " MB\n"
else
#print in GB
gb = byte_count/(1024*1024*1024)
print gb.to_i
print  " GB\n"
end
end

if ARGV[0] == "time"
array = Array.new
hours = Hash.new
hours = { "00" => 0, "01" => 0, "02" => 0, "03" => 0, "04" => 0, "05" => 0, "06" => 0, "07" => 0, "08" => 0, "09" => 0, "10" => 0, "11" => 0, "12" => 0, "13" => 0, "14" => 0, "15" => 0, "16" => 0, "17" => 0,"18" => 0, "19" => 0, "20" => 0, "21" => 0, "22" => 0, "23" => 0, "24" => 0}

for i in 0...lines.length
 if lines[i] =~ /(\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})\s(\-)\s(\-|\w+|\d+|\_)\s\[(\d{1,2}\/\w{1,3}\/\d{4}\:\d{2}\:\d{2}\:\d{2}\s\-\d{4})\]\s(\".+\")\s(\d{1,8})\s(\d{1,8})/
ip = $1
hyphen = $2
name = $3
date = $4
request = $5
status = $6
bytes = $7

date = date.split(/\/|:|\s/) #[day/month/year:hour:minute:second ,zone]  (array of 7)
array[i] = date
end
end

for i in 0...lines.length
hours[array[i][3]] += 1 
end

for i in 0...24
if i < 10
puts "#{"0" + i.to_s} #{hours["0" + i.to_s]}".sort
else
puts "#{i.to_s} #{hours[i.to_s]}".sort
end
end


#puts hours.keys.inspect.sort
#print "\n"
#puts hours.values.inspect.sort
#print "\n"
end

#popularity
if ARGV[0] == "popularity"
hours = Hash.new
arb_string = '\"' + '([\w\W^"]+)' + '\"'
for i in 0...lines.length
 if lines[i] =~ /(\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})\s(\-)\s(\-|\w+|\d+|\_)\s\[(\d{1,2}\/\w{1,3}\/\d{4}\:\d{2}\:\d{2}\:\d{2}\s\-\d{4})\]\s(\".+\")\s(\d{1,8})\s(\d{1,8})/
ip = $1
hyphen = $2
name = $3
date = $4
request = $5
status = $6
bytes = $7
date = date.split(/\/|:|\s/) #[day/month/year:hour:minute:second ,zone]  (array of 7)

if hours[request] == nil
  hours[request]  = 1
else
  hours[request] +=1
  #print hours[request]
end

end 

end 
count = 10
hours.sort.reverse{|a,b| a[1]<=>b[1]}.each { |elem|
  puts "#{elem[1]} #{elem[0]}"
  count = count - 1
    break if count == 0
}
end 