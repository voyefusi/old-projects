#!/usr/bin/ruby -w

tests = [ "testRecursion1", "testRecursion2", 
	"testHigherOrder1", "testPuzzle1", 
	"testPuzzle2", "testSolve1"] 

tests.each { |x|
	system("ocaml #{x}.ml > #{x}.log")
	if $? != 0
		puts "#{x} failed: run-time error"
	end
	system("diff #{x}.log #{x}.out")
	if $? != 0 
		puts "#{x} failed: incorrect output"
	else
		puts "#{x} passed"
	end
}
