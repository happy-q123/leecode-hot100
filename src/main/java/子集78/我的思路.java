package 子集78;

import java.util.ArrayList;
import java.util.List;

public class 我的思路 {
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        back(path, 0,  result, nums);
        return result;
    }

    public void back(ArrayList<Integer> path, int first, List<List<Integer>> result, int[] nums) {
        //对于第一次进来时，最终递归是添加以nums[i]开头的所有序列
        //比如在第一次进来时，i=0的递归到最后是添加了[1,2,3,4]、[1,2,3]、[1,2]、[1]。
        //i=1的递归到最后是添加了[2,3,4]、[2,3]、[2]
        for (int i = first; i <= nums.length; i++) {
            if (i == nums.length) {
                result.add(new ArrayList(path));
                continue;
            }

            path.add(nums[i]);
            back(path, i + 1,  result, nums);
            path.removeLast();

        }
    }
}
