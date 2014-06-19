#!/bin/bash
#
# run all the tests

ocamlc part3.ml
stuff="Eleanor Rigby, picks up the rice
In the church where a wedding has been
Lives in a dream
Waits at the window, wearing the face
That she keeps in a jar by the door
Who is it for
"

# need to turn off echo's trailing new line
echo -n "$stuff" | ./a.out

echo ""
echo "This is what I got"
echo ""
echo "ELANORspaceIGBY,PCKSUTHWDVMFJ"
echo "Character [space] appears 38 times."
echo "Character [E] appears 20 times."
echo "Character [I] appears 13 times."
echo "Character [A] appears 12 times."
echo "Character [H] appears 12 times."
echo "Character [R] appears 10 times."
echo "Character [T] appears 10 times."
echo "Character [N] appears 8 times."
echo "Character [S] appears 7 times."
echo "Character [W] appears 7 times."
echo "Character [O] appears 6 times."
echo "Character [C] appears 5 times."
echo "Character [D] appears 5 times."
echo "Character [B] appears 3 times."
echo "Character [G] appears 3 times."
echo "Character [P] appears 3 times."
echo "Character [,] appears 2 times."
echo "Character [F] appears 2 times."
echo "Character [K] appears 2 times."
echo "Character [L] appears 2 times."
echo "Character [U] appears 2 times."
echo "Character [Y] appears 2 times."
echo "Character [J] appears 1 times."
echo "Character [M] appears 1 times."
echo "Character [V] appears 1 times."
