package 颜色分类75;

class 我的解法 {

    //0,1,2
    public void sortColors(int[] nums) {
        int p0=-1;//0的最后一个元素下标
        int p1=-1;//1的最后一个元素下标
        int i=0;
        int length=nums.length;

        while(i<length){
            if(nums[i]==2){
                i++;
                continue;
            }

            if(nums[i]==0){
                if(nums[p0+1]==1){
                    nums[i]=1;
                    nums[p0+1]=0;
                    p0++;
                }else{
                    if(nums[p0+1]==2){
                        nums[p0+1]=0;
                        p0++;
                        nums[i]=2;
                        i++;
                    }else{
                        int x=nums[p0+1];
                        nums[p0+1]=1;
                        nums[i]=x;
                        p0++;
                        i++;
                    }

                }
                continue;
            }

            if(nums[i]==1){
                if(nums[p1+1]==0){
                    p1=p0;
                }else{
                    if(nums[p1+1]==2){
                        nums[p1+1]=1;
                        nums[i]=2;
                        p1++;
                        i++;
                    }else{
                        int x=nums[p1+1];
                        nums[p1+1]=1;
                        nums[i]=x;
                        p1++;
                        i++;
                    }

                }
                continue;
            }

        }
    }
}