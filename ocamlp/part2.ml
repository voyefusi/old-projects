(* CMSC 330 Summer 2012
 * Project 3 Part 2
 * Name: Victor Oyefusi
 *)

let rec itob i = 
if i = 0 then false::[]
else if i = 1 then true::[]
else let n = (float i)/.2.0 in
  if ((ceil n) = (floor n)) (*it's an int*) then false::(itob (truncate n))
  else true::(itob (truncate n))
;;


(*Write a function btoi : bool list -> int to convert from a list of booleans to an OCaml integer.*)
let btoi l =
let rec btoi_helper l i = match l with
[]->0
|h::t -> if h = true then int_of_float(2.0**(float_of_int i)) + (btoi_helper t (i + 1))
else  btoi_helper t (i + 1)
in btoi_helper l 0;;



let rec right_shift l = match l with
[]->[]
|[h] -> false::[]
|h::h2::t -> let h = h2 in h::right_shift (h2::t)
;;

let reverse l = 
    let rec reverse_helper l r = match l with
        [] -> r
      | h::t -> (reverse_helper t (h::r))
    in (reverse_helper l [])
;;

let left_shift_helper l = let lst = l in let lst2 = reverse lst in 
right_shift lst2;;
let left_shift l = List.rev(left_shift_helper l);; 

let shift l bool = if bool = true then right_shift l
else
left_shift l
;;

let rec combine l m = match l,m with
h::t,h2::t2 -> (h,h2)::(combine t t2)
|h::t, [] -> (h,false)::(combine t []) 
|[], h2::t2 -> (false, h2)::(combine [] t2)
|[],[] -> []
;;

let add l m = let x = combine l m in 
let rec add_helper lst carry = match lst with
[]->if(carry = true) then true::[]
else []
|(h1,h2)::t -> 
if ((h1 = true)&&(h2 = true)&&(carry = true))
then true::(add_helper t true)
else if ((h1 = true)&&(h2 = false)&&(carry = true))
then false::(add_helper t true)
else if ((h1 = false)&&(h2 = true)&&(carry = true))
then false::(add_helper t true)
else if ((h1 = false)&&(h2 = false)&&(carry = true))
then true::(add_helper t false)
else if ((h1 = true)&&(h2 = true)&&(carry = false))
then false::(add_helper t true)
else if ((h1 = true)&&(h2 = false)&&(carry = false))
then true::(add_helper t false)
else if ((h1 = false)&&(h2 = true)&&(carry = false))
then true::(add_helper t false)
else false::(add_helper t false)
in add_helper x false
;;


(*
let move f i j = 
if ((f (i+1) j = false) && (f i (j+1) = false)) then ((i+1),j)::(i,(j+1))::[]
else if((f (i+1) j = true) && (f i (j+1) = false)) then (i,(j+1))::[]
else if((f (i+1) j = false) && (f i (j+1) = true)) then ((i+1),j)::[]
else []
;;
*)

(*l is going to be an empty list. i and j need to start as 1*)
(*func l i j f m n -> match l with
[]-> if (f i j = true) then (i,j)::[]  *) (*(1,1) is the only path and we return that*)
(*else (move f i j)   *)               (*destination not blocked and we start here*)
(*|h::t*)



let rec print_list l = match l with 
[]-> Printf.printf "\n"
|(a,b)::t -> Printf.printf "(%d,%d)" a b; print_list t
;;



let paths f m n =
let rec path_helper (f, m, n, list) = if ((m = 1)&&(n = 1)) then
begin
let b =  list@[(m,n)] in  print_list b;1
end
else if ((m > 1)&&(n > 1)&&(f m n = false)) then
let g = list@[(m,n)] in 0 + (path_helper( f,(m-1),n,g))+ 0 + path_helper(f,m,(n-1),g);
else if ((m = 1)&&(n > 1)&&(f m n = false)) then
let u = list@[(m,n)] in 0 + path_helper(f,m,(n-1),u);
else if ((m > 1)&&(n = 1)&&(f m n = false)) then
let g = list@[(m,n)] in 0 + (path_helper( f,(m-1),n,g))
else 0

in path_helper (f,m,n,[])
;; 
