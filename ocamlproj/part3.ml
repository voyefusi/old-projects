(* CMSC 330 Summer 2012
 * Project 3 Part 3
 * Name: Victor Oyefusi
*)
let rec read_all () =
    try (
        let line = String.make 1 (input_char stdin) 
        in line ^ "" ^ read_all ()
    ) with End_of_file -> ""
;;

(*print_string (read_all ());;*)

let reverse l = 
    let rec reverse_helper l r = match l with
        [] -> r
      | h::t -> (reverse_helper t (h::r))
    in (reverse_helper l [])
;;

let rec sort lst =
   match lst with
     [] -> []
   | (a,b)::t -> insert a b (sort t)
 and insert ch elt lst =
( match lst with
     
  [] -> [(ch,elt)]
   | (h,h2)::t -> if elt <= h2 then (ch,elt)::lst else (h,h2) :: insert ch elt t
(*change to is equal sort by ascii*)
)
;;

let print_info s =
   Printf.printf "%s" s
;;

let remove_elt e l =
  let rec go l acc = match l with
    | [] -> List.rev acc
    | x::xs when e = x -> go xs acc
    | x::xs -> go xs (x::acc)
  in go l []

let remove_duplicates l =
  let rec go l a = match l with
    | [] -> List.rev a
    | h :: ht -> go (remove_elt h ht) (h::a)
  in go l []
;;

let explode s =
  let rec exp i l =
    if i < 0 then l else exp (i - 1) (s.[i] :: l) in
  exp (String.length s - 1) []
;;

let rec print_infos s = match s with
[]->()
|h::t-> print_char h;print_infos t
;;

let rec escape l = match l with
[]->[]
|h::t -> let g = (Char.escaped h) in g::(escape t)

;; 

let rec same_freq l = match l with
[]->[]
|(a,b)::(c,d)::t -> if(b = d) then
(
if((int_of_char a) < (int_of_char c))
    then (c,d)::(a,b)::same_freq t
else
 (a,b)::(c,d)::same_freq t
)
else
(a,b)::(c,d)::same_freq t
|(a,b)::t -> (a,b)::same_freq t
;;

(* count number of occurrences of char x in string s *)
let rec strcount x s =
   (if (String.length s = 1) then
      0
   else
      (strcount x (String.sub s 1 ((String.length s)-1))))
   + (if (s.[0] = x) then 1 else 0)
;;

let rec process l str = match l with
[]->[]
|h::t -> (h,(strcount h str))::(process t str)
;;

let rec change_to_spaces l = match l with
[]->[]
|h::t ->  if((h = '\n')||(h = '\t')) then 
let h = ' ' in h::(change_to_spaces t)
else h::(change_to_spaces t)
;;

let white_tuple ls =
let rec white_tuple_h l a = match l with
[]-> [("space", a)]
|(h,h2)::t ->
 if((h = '\n')||(h = ' ')||(h = '\t'))then
    begin
    let b = a + h2 in (white_tuple_h t b)
     end
else
    (white_tuple_h t a)
in white_tuple_h ls 0;;


let rec print_tuple l = match l with
[]->[]
|(a,b)::t ->
 if(a = ' ') then 
   begin
     (Printf.printf "Character[%s] appears %d times.\n" "space" b;
print_tuple t)
   end

else
(Printf.printf "Character[%c] appears %d times.\n" a b;
print_tuple t)
;;

let rec print_white_space l = match l with
[]-> (Printf.printf "")
|(a,b)::t -> (Printf.printf "Character[%s] appears %d times.\n" a b;
print_white_space t)
;;

let implode l =
  let res = String.create (List.length l) in
  let rec imp i = function
  | [] -> res
  | c :: l -> res.[i] <- c; imp (i + 1) l in
  imp 0 l;;

let rec spacify l = match l with
[]->[]
|h::t -> if h = ' ' then 's'::'p'::'a'::'c'::'e'::(spacify t)
else
h::(spacify t);;

let rec n_times_sort l n =
if (n >= 0) then
let y = same_freq l in n_times_sort y (n-1)
else
l
;;
let l = (read_all ()) (*load_file "text.txt"*);;
let l = String.uppercase l;;
let ex = explode l;;
let ex = change_to_spaces ex;;
let new_string = implode ex;;
let clean = remove_duplicates ex;;
let es = escape clean;;
let sp = spacify clean;;
let implosion = implode sp;;
print_string implosion;;
Printf.printf "\n";;
let tuple = process clean new_string;;
let whitey = white_tuple tuple;; 
let sorted = sort tuple;;
let sorted = n_times_sort sorted (String.length l);; 
(*print_white_space whitey;;*) 
let sorted = List.sort(fun x y->match x,y with
(a,b),(c,d)->if b>d then 0 
else if b=d then if Char.code a > Char.code c then 1 else 0
else 1)
sorted
;;
let descending = reverse sorted;;
print_tuple sorted;;


