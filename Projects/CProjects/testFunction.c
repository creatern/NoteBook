#include <stdio.h>

void bulter(void); // 函数原型/函数声明

int main(void){
	printf("Hello, function\n");
	bulter(); // 函数调用
	printf("OK!");
	return 0;
}

// 函数定义
void bulter(void){
	printf("Bulter is called.\n");
}
