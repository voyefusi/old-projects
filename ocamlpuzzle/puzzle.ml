(* CMSC 330 / Project 3 *)
(* Student: Victor Oyefusi *)

(* Fill in the implementation and submit puzzle.ml *)

#use "testUtils.ml";;

(*-------------------------------------------------------------------------*)
(* functions from lecture you may use *)

let rec map (f, l) = match l with
    [] -> []
  | (h::t) -> (f h)::(map (f, t))
;;

let rec fold (f, a, l) = match l with
    [] -> a
  | (h::t) -> fold (f, f (a, h), t)
;;

let length x = fold ((fun (a,y) -> a+1), 0, x)
;;

let rev x = fold ((fun (a,y) -> y::a), [], x)
;;

(*-------------------------------------------------------------------------*)
(* Part 1: Recursion *)
 
(* 
get_val (x, n) 	
int list * int -> int 	
element of list x at index n, or -1 if not found
  (indexes start at 0)
Example: get_val ([5;6;7;3],1) => 6
*)

(*x is int list. n is index. take off each head then decrement n by 1 til n gets to zero. if an empty list is shown we have gone out of bounds. otherwise the head at n = 0 is the value we want*) 

let rec get_val (x,n) = 
	match x with
	[] -> -1
	|(h::t)->if(n = 0) then h
	else(get_val(t, n-1))
;;

(* 
get_vals (x, y) 	
int list * int list -> int list 	
list of elements of list x at indexes in list y, 
[] if any indexes in y are outside the bounds of x;
elements must be returned in order listed in y 
Example: get_vals ([5;6;7;3],[2;0]) => [7;5]
*)

let rec get_vals (x, y) = 
match y with
[]->[]
|(h::t) -> let i = get_val(x,h) in 
if(i = -1) then get_vals(x,t) else i::get_vals(x,t)
;;	

(* 
set_n (x, n, v) 	
'a list * int * 'a -> 'a list 	
list produced by setting n'th element of list x to value v 	
no effect if n is outside the bounds of the list 
Example: set_n ([5;6;7;3],1,9) => [5;9;7;3]
*)

let rec set_n (x, n, v) = match x with
[]->[] 
|h::t -> if(n = 0) then let h = v in h::set_n(t, n-1, v)
 else h::set_n(t, n-1, v)
;;


(* 
list_swap_val (b, u, v) 	
'a list * 'a * 'a -> 'a list 	
list b with values u,v swapped 	
(change value of multiple occurrences of u and/or v, if found, and
change value for u even if v not found in list, and vice versa )
Example: list_swap_val ([5;6;7;3],7,5) => [7;6;5;3]
*)

let rec list_swap_val (b, u, v) = 
match b with
[]->[]
|h::t -> if(h = u) then let h = v in h::list_swap_val (t, u, v)
else if(h = v) then let h = u in h::list_swap_val (t, u, v);
else h::list_swap_val (t, u, v)
;;

(*is there an element that equals x in l? *)
let rec contain (x,l) =
   match l with
      [] -> false
    | (h::t) -> (h = x) or (contain (x,t))
;;


(* 
index (x, v) 	
'a list * 'a -> int 	
index of value v in list x, or -1 if not found
  (indexes start at 0)
Example: index ([5;6;7;3],7) => 2
*)


let rec index (x,v) = 
if(contain (v, x)) then   
match x with
      [] -> 0
    | (h::t) ->
         if (v = h) then 0
         else 1 + index(t,v)
else 
-1
;;

(* 
uniq x 	
'a list -> 'a list 	
list of uniq elements in x 	
order of unique elements does not matter
Example: uniq [5;6;5;3] => [6;5;3]
*)

let rec uniq x = 
match x with
[]->[]
|h::t -> if(contain(h, t)) then uniq t
else h::uniq t
;;

(* 
find_new (x, y) 	
'a list * 'a list -> 'a list 	
list of members of list x not found in list y 	
maintain relative order of elements in result
Example: find_new ([4;3;7],[5;6;5;3]) => [4;7]
*)

(*need to figure out how to reverse the list created*)
let rec find_new (x,y) = 
match x with
[]->[]
|h::t -> if(contain(h, y)) then find_new(t, y)
else h::find_new(t, y)
;;

(* 
is_sorted x 	
'a list -> bool 	
true if elements in x are in sorted order, false otherwise 	
  (return true for [])
Example: is_sorted ([5;5;7;9]) => true 
*)

let rec is_sorted x = match x with
[] -> true
|[x]->true
|h::t -> if (h > get_val(t, 0)) then false
else is_sorted t
;;



(*-------------------------------------------------------------------------*)
(* Part 2: Higher order functions *)


(* 
grow_lists (x, y) 	 
'a list * 'a list -> 'a list list 	 
return a list of lists, where each element of x is prepended to y
resulting lists must be in same order as in x
Example: grow_lists ([1;2], [3;4]) => [[1;3;4]; [2;3;4]]
*)

let grow_lists (x,y) = map((fun  a -> a::y), x);;
(* 
concat_lists x 	
'a list list -> 'a list 	
return a list consisting of the lists in x concatenated together
(note just top level of lists is concatenated, unlike List.flatten)
Examples: concat_lists [[1;2];[7];[5;4;3]] => [1;2;7;5;4;3]
concat_lists [[[1;2;3];[2]];[[7]]] => [[1;2;3];[2];[7]]
*)

let concat_helper (a,b) = a@b;;

let concat_lists x = fold(concat_helper, [], x);;

(*-------------------------------------------------------------------------*)
(* Part 3: Puzzle functions *)

(* 
find_board_size b 	 
'a list -> int 	 
return size (that is, the length/width of a side) of board b 
represented as a list (assume board is square)
Example: find_board_size [1;0;2;3;4;5;6;7;8] => 3
*)
(* returns the length of l *)
let rec length l =
   match l with
      [] -> 0
    | (_::t) -> 1 + (length t)
;;

let find_board_size b = truncate(sqrt(float(length b)))
;;
(*
pos_of_xy (x, y, s) 	
int * int * int -> int 	
index of x, y coordinate in a list representing a board of size s
return -1 if x or y is out of bounds (i.e., less than 0 or greater than s-1)
Example: pos_of_xy (1, 2, 3) => 5
*)

let pos_of_xy (x, y, s) = if (x < 0) then -1
else if (y < 0) then -1
else if (x > (s-1)) then -1
else if (y > (s-1)) then -1
else ((s*x)+y)
;;


(* 
xy_of_pos (p, s) 	
int * int -> int * int 	
x, y coordinate of index p in a list representing a board of size s
may assume p is a legal position between 0..s-1
Example: xy_of_pos (5, 3) => (1, 2)
*)

let xy_of_pos (p, s) = let x = (p/s) in let y = p mod s in (x,y)
;; 


(* 
move_pos b 	
int list -> int list 	
list of positions in board that can move to space in board
positions must be in sorted order, from smallest to largest
Example: move_pos [0;1;2;3;4;5;6;7;8] => [1;3]
*)
let x_value (a,b) = a + 0;;

let y_value (a,b) = b + 0;;

let rec process_list l = match l with
[]->[]
|h::t -> if(h < 0) then process_list t
else h::process_list t
;;

let move_pos b = let s = (find_board_size b)
in let xy = (xy_of_pos(index(b,0),s)) in
let the_list = [(pos_of_xy(x_value(xy)-1, y_value(xy), s));(pos_of_xy(x_value(xy), y_value(xy)-1, s));(pos_of_xy(x_value(xy), y_value(xy)+1, s));(pos_of_xy(x_value(xy)+1, y_value(xy), s))] in
process_list the_list
;;
(* 
make_move (b,x) 	
int list * int -> int list 	
configuration of board after movin g number at position x to space
may assume position x is adjacent to space
Example: make_move ([0;1;4;5;2;3;6;7;8], 3) => [5;1;4;0;2;3;6;7;8] 
*)
let make_move (b, x) = list_swap_val (b, get_val(b, x), 0) ;;

(* 
make_moves b 	
int list -> int list list 	
boards produced after all possible 1-step moves for board b
boards must be in sorted order, with space in smallest position to largest
Example: make_moves [0;1;2;3;4;5;6;7;8] => 
	[[1;0;2;3;4;5;6;7;8];[3;1;2;0;4;5;6;7;8]] 
*)
let make_moves b = let move i = make_move (b,i) in map(move, move_pos b);;

(*-------------------------------------------------------------------------*)
(* Part 4: Puzzle solver *)

(* 
solve_board (b,n) 	 
int list * int -> int list list list

Given board b, return all solutions of length n, or [] if none exists.
A solution to solve_board is a list of boards produced by moves
starting from b until the solved board is reached.  The list is in
reverse order: solved board first, b last.  

Example: solve_board ([1;2;0;3;4;5;6;7;8],2) => 
	[[[0;1;2;3;4;5;6;7;8];[1;0;2;3;4;5;6;7;8];[1;2;0;3;4;5;6;7;8]]]

The length l of each solution is the number of moves (i.e., one less
than the length of the list that represents the solution).  Solutions
are not permitted to contain the same intermediate board twice.  For
example,
[[[0;1;2;3;4;5;6;7;8];[1;0;2;3;4;5;6;7;8];[0;1;2;3;4;5;6;7;8];[1;0;2;3;4;5;6;7;8];[1;2;0;3;4;5;6;7;8]]]
is not a legal length-4 solution to [1;2;0;3;4;5;6;7;8].</p> The order
of possible solutions does not matter.
 
Hints: as you are required to produce *all* solutions up to length n,
you are essentially doing an exhaustive search with a bit of smarts to
prune out paths containing duplicate boards.  That is, at each step
you will want to enumerate all possible boards produced by legal moves
from the current board of each path produced by prior steps.  You will
prune out paths that would be produced by repeating a previous board
position.  You should be making good use of the functions you have
already defined above.  If your solution is not using many of these
functions, you are doing too much work!
*)
let solve_board (b, n)= let rec board_helper i = match i with 
[] -> []
| (h::t) -> if (is_sorted(h)) then [i]
else if (index(t, h) > -1) then []
else if (length (i) > n) then []
else let j = grow_lists((make_moves h), i) in 
concat_lists(map(board_helper, j)) in 
board_helper [b]
;;


(*-------------------------------------------------------------------------*)
(* Bonus debugging utilities *)

(* print board as nXn square, with space @ 0 *)
let print_board b = 
    let board_size = (find_board_size b) in 
    let rec print_board_helper (b, x) = match b with
	  [] -> print_endline "------------------------"
	| (h::t) -> 
		print_string (if (h < 10) then "   " 
		 	else if (h < 100) then "  " else " ");
		if (h = 0) then print_string " " else (print_int h) ; 
		(if ((x mod board_size) = 0) then print_endline "") ; 
		print_board_helper (t, x+1)
    in print_board_helper (b, 1)
;;

(* print list of boards *)
let print_boards x = 
	print_endline "------------------------" ;
	ignore (map (print_board, x)) ;
	print_endline ""
;;

(* print some boards & lists of boards *)

(* uncomment following to test print! *)
(*
let try_out_print =
	let b1 = [0;1;2;3;4;5;6;7;8] in
	let b2 = [1;0;2;3;4;5;6;7;8] in
	let blist = [b1;b2] in
		prt_int_list b1;
		print_board b1;
		prt_int_list b2;
		print_board b2;
		prt_int_list_list blist;
		print_boards blist
;;
*)
