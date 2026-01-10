package 接雨水;

class 我的思路 {

    public static void main(String[]args){
//        int []height=new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int []height=new int[]{9,6,8,8,5,6,3};
        System.out.println(trap(height));
    }
    public static int trap(int[] height) {
        int x=0;
        int jian_st=-1;
        int left_min=-1;
        int shi_ti=0;
        int result=0;
        int right_end=-1;
        int right_max=-1;
        while(x<height.length){
            //起初连续0的情况
            if(height[x]==0&&jian_st==-1){
                x++;
                continue;
            }

            //不为0的情况
            if(jian_st==-1){
                jian_st=x;
                left_min=height[x];

                //一旦确定了jian_st，我需要向后寻找大于等于这个jian_st对应值的元素，将其作为新的jian_st，并计算雨水。
                //如果没有找到大于等于的，则需要找到后面区间最大的那个。并计算雨水。然后以最大的那个作为新的jian_st。

                int flag=0;
                x++;
                shi_ti=0;
                while(x<height.length){
                    //寻找递减序列最小值
                    if(left_min>=height[x]){
                        shi_ti+=height[x];
                        left_min=height[x];
                        x++;
                    }else{
                        if(height[x]<height[jian_st]){
                            shi_ti+=height[x];
                            //保留右边最大的那个（以防止右边找不到大于等于jian_st的值了）
                            if(right_max==-1){
                                right_max=height[x];
                                right_end=x;
                            }else{
                                if(right_max<=height[x]){
                                    right_max=height[x];
                                    right_end=x;
                                }
                            }
                            x++;
                        }else{
                            //找到大于等于jian_st的值了

                            //计算总面积
                            int zong=(x-jian_st+1)*height[jian_st];
                            //减去shi_ti,再减去两边界阻挡的雨水
                            result+=zong-shi_ti-height[jian_st]*2;
                            System.out.println("result:"+result);

                            flag=1;
                            //初始化
                            jian_st=-1;
                            left_min=-1;
                            shi_ti=0;
                            right_end=-1;
                            right_max=-1;
                            break;
                        }
                    }

                }
                //右边没找到比大于等于jian_st的
                if(flag==0) {
                    //如果右边无增加的值了，直接结束吧。
                    if (right_end == -1) {
                        break;
                    }

                    x = right_end;
                    int zong = (right_end - jian_st + 1) * right_max;
                    shi_ti=0;
//                    从新计算shi_ti
                    for(int i=jian_st;i<=right_end;i++){
                        if(height[i]>right_max)
                            shi_ti+=right_max;
                        else
                            shi_ti+=height[i];
                    }
                    result += zong - shi_ti;
                    System.out.println("result:"+result);
                    jian_st = -1;
                    right_end = -1;
                    right_max = -1;
                }
            }
        }
        return result;
    }
}