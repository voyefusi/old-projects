#include <stdio.h>
#include "puzzles.h"
static size_t correct_sizeof_long();
static unsigned int correct_reverse_bytes();
static unsigned int correct_hex();
static unsigned int correct_get_byte();
static int correct_equal();
static void test_case_sizeof_long();
static void test_case_reverse_bytes();
static void test_case_hex();
static void test_case_get_byte();
static void test_case_equal();

int main() {
  test_case_sizeof_long();
  test_case_reverse_bytes();
  test_case_hex();
  test_case_get_byte();
  test_case_equal();
  return 0;
}

static size_t correct_sizeof_long(){
  long int test = 999999999999999999;  
  return sizeof(test);
  
}

static void test_case_sizeof_long(){
    int correct_result, my_result;
    correct_result = correct_sizeof_long();
    my_result = sizeof_long();
    
    if (my_result == correct_result) {
      printf("PASSED!\n");
    }
    else {
      printf("FAILED. (Got %d, should be %d.)\n", my_result, correct_result);    
    }
  }


static unsigned int correct_reverse_bytes(){
  unsigned int test = 0x78563412;
    return test;
    
}
static void test_case_reverse_bytes(){
  unsigned int correct_result, my_result;
  correct_result = correct_reverse_bytes();
  my_result = reverse_bytes(0x12345678);
  
  if (my_result == correct_result) {
    printf("PASSED!\n");
  }
  else {
    printf("FAILED. (Got %u, should be %u.)\n", my_result, correct_result);
    
  }     
  
}

static unsigned int correct_hex(){
  unsigned int coco = 0xc0c0c0c0;
  return coco;
}
static void test_case_hex(){
  unsigned int correct_result, my_result;
  correct_result = correct_hex();
  my_result = hex_c0c0c0c0(0xc0c0c0c0);
  
  if (my_result == correct_result) {
    printf("PASSED!\n");
  }
  else {
    printf("FAILED. (Got %x, should be %x.)\n", my_result, correct_result);
  }     
}

static unsigned int correct_get_byte(int n){
  unsigned int a = 0x0a; 
  unsigned int b = 0x0b;
  unsigned int c = 0x0c;
  unsigned int d = 0x0d;
  
  if(n==0){
    return a;
  }else if(n==1){
    return b;
  }else if(n==2){
    return c;
  }else{/*assuming n==3*/
    return d;
  }
}


static void test_case_get_byte(){
  unsigned int correct_result, my_result;
  correct_result = correct_get_byte(2);
  my_result = get_byte(0x0a0b0c0d, 2);
  
  if (my_result == correct_result) {
    printf("PASSED!\n");
  }
  else {
    printf("FAILED. (Got %x, should be %x.)\n", my_result, correct_result);
  }     
}

static int correct_equal(){
  return 1; 
}
static void test_case_equal(){
  unsigned int correct_result, my_result;
  correct_result = correct_equal();
  my_result = equal(2, 2);
  
  if (my_result == correct_result) {
    printf("PASSED!\n");
  }
  else {
    printf("FAILED. (Got %d, should be %d.)\n", my_result, correct_result);
  }     
}



