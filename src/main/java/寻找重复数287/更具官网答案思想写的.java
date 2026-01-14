package 寻找重复数287;

public class 更具官网答案思想写的 {
    public int findDuplicate(int[] nums) {
        int kuai=0;
        int man=0;
        int n=nums.length;
        int x=nums[0];

        //寻找环点
        while(true){
            kuai=nums[kuai];
            kuai=nums[kuai];
            man=nums[man];
            if(kuai==man)
                break;
        }

        //让man回到头部，继续遍历，再次相遇时，就是重复的那个数
        //这次每个指针都只走一部
        /**
         原理：
         设头部到环的入口距离为q，入口到第一次相遇位置为x。
         那么相遇时，man走的距离是q+x。
         kuai每次走2步，man每次走一步，所以kuai走的距离为2*(q+x)
         第一次相遇时，kuai比man多走一个环的距离。
         因此有 q+x+一个环的距离=2q+2x，
         移项得 一个环的距离=q+x，再得q=一个环的距离-x=相遇点到环入口的剩下路程
         所以有a从头部走，b从相遇点走，每次走一步，两者会在入口相遇。
         */
        man=0;
        while(kuai!=man){
            kuai=nums[kuai];
            man=nums[man];
        }
        return kuai;

    }
}
