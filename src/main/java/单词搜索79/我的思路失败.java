package 单词搜索79;

public class 我的思路失败 {
    public static void main(String[] args) {
        我的思路失败 x=new 我的思路失败();
        char[][] d=new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String s="SEE";
        String s="ABCCED";
        boolean lo=x.exist(d,s);
        System.out.println(lo);
    }
    /*
        取行数 (Rows)
        int m = board.length;

        获取列数 (Cols)
        前提：数组不为空，且是一个标准的矩阵（每行长度一致）
        int n = board[0].length;
    * */
    public boolean exist(char[][] board, String word) {
        return compute(0,0,0,board,word);
    }

    //设计错误
    //当前层应该去匹配word中下标为index的字符、当前行号、当前列号、原始的字符矩阵、待匹配的字符word
    public boolean compute(int index,int hang,int lie,char[][] board,String word){
        //本层待匹配的字符
        char destination=word.charAt(index);
        System.out.print(board[hang][lie]+" ");

        boolean current_result=false;
        int next_index=index;

        if(destination==board[hang][lie]){
            current_result=true;
            next_index=next_index+1;
        }

        //最后一个index，直接返回，不在往下递归
        if(index==word.length()-1){
            return current_result;
        }


        board[hang][lie]='1';
        boolean next_result=false;
        //index<word.length()-1的情况
        //一个位置最多四个相邻位置
        //board[0].length表示列数、board.length表示行数
        if(hang+1<board.length&&board[hang+1][lie]!='1'){
            next_result=compute(next_index,hang+1,lie,board,word);
            if(next_result){
                return true;
            }
        }

        if(hang-1>=0&&board[hang-1][lie]!='1'){
            next_result=compute(next_index,hang-1,lie,board,word);
            if(next_result){
                return true;
            }
        }
        //board[0].length表示列数、board.length表示行数
        if(lie+1<board[0].length&&board[hang][lie+1]!='1'){
            next_result=compute(next_index,hang,lie+1,board,word);
            if(next_result){
                return true;
            }
        }
        if(lie-1>=0&&board[hang][lie-1]!='1'){
            next_result=compute(next_index,hang,lie-1,board,word);
            if(next_result){
                return true;
            }
        }
        return false;
    }

}
