#include "ResultInterface.h"
#include<stdlib.h>
#include<time.h>
#define maxNum 16

int sweepMap[maxNum][maxNum];
int rows;
int columns;


int mymin(int x,int y){
    return x<y?x:y;
}
int mymax(int x,int y){
  return x>y?x:y;
}
JNIEXPORT void JNICALL Java_ResultInterface_init
  (JNIEnv *env, jobject obj, jint row_count, jint column_count, jint mine_num)
{
    int i;
    int j;
   // int count;
    int tmp1;
    int tmp2;
    rows = row_count;
    columns = column_count;
	if (mine_num >= 5)
		mine_num = 5;
    for(i=0;i<rows;i++)
        for(j=0;j<columns;j++){
            sweepMap[i][j] = 0;
        }
    srand(time(NULL));
    for(i=0;i<mine_num;i++){
        tmp1 = rand()%rows;
        tmp2 = rand()%columns;
        if(sweepMap[tmp1][tmp2] == 0){
            sweepMap[tmp1][tmp2] = -1;
        }else{
            i--;
        }
    }
}

JNIEXPORT jint JNICALL Java_ResultInterface_countMines
  (JNIEnv *env, jobject obj, jint row, jint column)
{

    int i = 0;
    int j = 0;
    int mycount = 0;
    if(sweepMap[row][column]==-1)   return -1;
    for(i = mymax(row-1,0);i<=mymin(row+1,rows);i++){
        for(j = mymax(0,column-1);j<=mymin(column+1,columns);j++){
            if (sweepMap[i][j]==-1){
                mycount++;
            }
        }
    }
    return mycount;

}




