#include <stdio.h>
#include "puzzles.h"

int bit_or(int a, int b){
  /*false if a and b are both false, true otherwise*/
  return ~(~a&~b);    
}
int is_nonzero(int x){
  /*negates x twice so that any int will return 1 and zero
    will return zero*/
  return ~~x;
}

unsigned int times7(unsigned int x){
  /*x shift 2 + x + x + x == 7x*/
  return ((x << 2) + x + x + x); 

}
int floor_log8(unsigned int x){
  /*If x == 0, returns -1. Otherwise
    inside for loop x shifts by 3 (2^8 = 8) 
    and counter counts each floor until x
    shifts to zero*/
  int count = 1;
  int neg_one = 1;
  neg_one--;
  neg_one--;
  count--;
  for(count = neg_one;x;count++){
    x = x>>(2+1);
  }
  return count;
}

size_t sizeof_long(){
  /*declared an long int and assigned all 1 bits to it.
    Then I shifted 8 bits(1 byte) to the right within a loop
    incrementing a counter by 1 each time until the long int 
    reaches zero. I return the count to obtain the correct
    amount of bytes*/
  int count = 1;
  unsigned long all_ones = 1;  
  count--;
  all_ones--;
  all_ones = ~all_ones;  

  for(count = 1-1;all_ones ;count++){
    all_ones = all_ones >> 8;
  }
  return count;
}  

unsigned int reverse_bytes(unsigned int x){
  /*Made four copies of the unsigned int x
Shifted the copies truncating them so that 
they represent a specific byte of unsigned int x.
I shift the bytes in the proper locaion needed to
reverse the unsigned int*/

  unsigned int first = x >> (16+8);
  unsigned int second = x << 8;
  unsigned int third = x << 16;
  unsigned int fourth = x << (16+8);
  
  fourth = fourth >> (16+8);
  second = second >> (16+8);
  third = third >> (16+8);
  
  second = second << 8;
  third = third << 16;
  fourth = fourth << (16+8);
  return first | second | third | fourth;
}
unsigned int hex_c0c0c0c0(){
  /*(c = 1100 = twelve)
    shifted copies of twelve the appropriate 
    number of bytes to create the specified 
    unsigned int
  */
  
  unsigned int twelve = (8+4);
  
  return ((twelve<<4) | (twelve<<(8+4)) | (twelve<<(16+4)) | (twelve<<(16+8+4)));
}

int pop_count(unsigned int x){
  /*shift integer x one bit at a time,
    using and bit with 1. shifts one at
    a time adding to the counter until
    x is zero
  */
  int count = 1;
  count--;
  while(x){
    count = count + (x&1);
    x = (x>>1);  
  }  
  
  return count;
}

unsigned int get_byte(unsigned int x, int n){ 
  /*Shifted 8*n bits to the left to truncate 
    bits I didn't need, then shifted
    24 bits to the right to place nth significant
    byte in the least significant spot*/
  
  x = x << (n <<(2+1));
  x = x >> (16+8);
  return x;
}
int equal(int a, int b){
  /*if a and b are both true a and not b is false.
    negating it when it's false makes it 
    true and vice versa */
  return  ~~(!(a&~b));
}

