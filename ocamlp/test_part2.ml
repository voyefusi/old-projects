(* printing boolean lists *)

#use "part2.ml"

let rec print_bool_list l =
	match l with
		[] -> ()
		| h::[] ->
			print_string (string_of_bool h);
			print_string "]";
		| h::t -> 
			print_string (string_of_bool h);
			print_string "; ";
			print_bool_list t
;;

let print_bool_list_first l =
	match l with 
		[] -> print_string "[]"
		| h::[] -> 
			print_string "[";
			print_string (string_of_bool h);
			print_string "]";
		| h::t -> 
			print_string "[";
			print_string (string_of_bool h);
			print_string "; ";
			print_bool_list t
;;

print_string "Expecting [false; true; true; false; true]: ";;
print_bool_list_first (itob 22);;
print_string "\n";;

print_string "Expecting 42: ";;
let l = [false; true; false; true; false; true];;
print_int (btoi l);;
print_string "\n";;

(*   shift tests   *)
let l = [false; true; false; true; false; true];;
print_string "Expecting [false; false; true; false; true; false]: ";;
print_bool_list_first (shift l false);;
print_string "\n";;

let l = [false; true; false; true; false; true];;
print_string "Expecting [true; false; true; false; true; false]: ";;
print_bool_list_first (shift l true);;
print_string "\n";;

(*  add tests  *)

let l1 = [false; false; true; true; false];;
let l2 = [true; true; true; true; true];;
print_string "Expecting [true; true; false; true; false; true]: ";;
print_bool_list_first (add l1 l2);;
print_string "\n";;

let l1 = [true; true];;
let l2 = [true; true];;
print_string "Expecting [false; true; true]: ";;
print_bool_list_first (add l1 l2);;
print_string "\n";;

let l1 = [false; false];;
let l2 = [false; false];;
print_string "Expecting [false; false]: ";;
print_bool_list_first (add l1 l2);;
print_string "\n";;

(* paths tests *)

let f m n = if m = 2 && n = 2 then true else false;;
print_string "Expecting (3,3)(3,2)(3,1)(2,1)(1,1) and (3,3)(2,3)(1,3)(1,2)(1,1)\n";;
paths f 3 3;;

let f m n = if (((m = n) && (m <> 1)) || ((n > 3) && ((m mod (n / 2)) = 0))) then true else false;;
print_string "I'm not writing them out. There should be 14 of them though\n";;
paths f 7 6;;
