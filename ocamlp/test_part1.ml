#use "part1.ml"

(* prod tests *)
let l = [];;
print_string "Expecting: 1 ";;
print_int (prod l);;
print_string "\n";;

let l = [2;3;4;5;6];;
print_string "Expecting: 720 ";;
print_int (prod l);;
print_string "\n";;

let l = [4];;
print_string "Expecting: 4 ";;
print_int (prod l);;
print_string "\n";;


(* index tests *)

let l = [1;1;1;1;1;1];;
print_string "Expecting 5: ";;
print_int (index l 1);;
print_string "\n";;

let l = [];;
print_string "Expecting -1: ";;
print_int (index l 0);;
print_string "\n";;

let l = [1;2;3;4;5;6;6;5;4;3;2;1];;
print_string "Expecting 8: ";;
print_int (index l 4);;
print_string "\n";;

(* unzip tests *)

(* print char lists *)
let rec print_char_list l =
	match l with
		[] -> ()
		| h::[] ->
			print_string "'";
			print_char h;
			print_string "'";
			print_string "]";
		| h::t -> 
			print_string "'";
			print_char h;
			print_string "'; ";
			print_char_list t
;;

let print_char_list_first l =
	match l with 
		[] -> print_string "[]"
		| h::t -> 
			print_string "['";
			print_char h;
			print_string "'; ";
			print_char_list t
;;

(* print int lists *)
let rec print_int_list l =
	match l with
		[] -> ()
		| h::[] ->
			print_int h;
			print_string "]";
		| h::t -> 
			print_int h;
			print_string "; ";
			print_int_list t
;;

let print_int_list_first l =
	match l with 
		[] -> print_string "[]"
		| h::[] -> 
			print_string "[";
			print_int h;
			print_string "]"
		| h::t -> 
			print_string "[";
			print_int h;
			print_string "; ";
			print_int_list t
;;

(******** actual zip tests *****)
let l = [(1,'a'); (2, 'b'); (3, 'c'); (4,'d')];;
print_string "Expecting [1; 2; 3; 4] ['a'; 'b'; 'c'; 'd']: ";;
let (a,b) = unzip l;;
print_int_list_first a;;
print_string " ";;
print_char_list_first b;;
print_string "\n";;

let l = [];;
print_string "Expecting [] []: "
let (a,b) = unzip l;;
print_int_list_first a;;
print_string " ";;
print_char_list_first b;;
print_string "\n";;

(*    app_int tests    *)

let rec factorial n =
	if n < 2 then 1 else n * (factorial (n-1))
;;

let l = app_int factorial 3 7;;

print_string "Expecting [6; 24; 120; 720; 5040]: ";;
print_int_list_first l;;
print_string "\n";;

let l = app_int factorial 3 3;;

print_string "Expecting [6]: ";;
print_int_list_first l;;
print_string "\n";;
