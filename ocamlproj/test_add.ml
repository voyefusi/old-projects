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

