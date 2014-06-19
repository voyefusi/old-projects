(* CMSC 330 Summer 2012
 * Project 3 Part 1
 * Name: 
 *)

let rec prod l = match l with
[]->1
|h::t -> h*(prod t)
;;

(* count number of occurrences of x in l *)
let rec count (x,l) =
   match l with
      [] -> 0
    | (h::t) ->
         if (x = h) then 1 + (count (x,t))
         else (count (x,t))
;;

let rec contain (x, l) = 
match l with
[]-> false
|(h::t) -> (h = x) or (contain (x,t))
;;


(*let rec index_helper l e = if(contain(e, l)) then
match l with
[] -> 0
|(h::t) ->
if(e = h) then 0
else 1 + (index_helper t e) 
else
-1
;;*)

let index l e = 
  let rec index_helper i n l = 
    match l with
h::t -> index_helper (i+1) (if h = e then i else n) t
    |[]->n
in index_helper 0 (-1) l;;

let rec app_int f m n = 
if (m < n) then (f m)::(app_int f (m+1) n)
else if(m=n) then f n::[]
else
[]
;;

let rec unzip l = match l with
[] -> ([],[])
|h::t -> match h with
(a,b) -> (a::first t,b::second t)

and first l = match l with
[]->[]
|h::t -> match h with
(a,b) -> a::first t 

and second l = match l with
[]->[]
|h::t -> match h with
(a,b) -> b::second t
;;




