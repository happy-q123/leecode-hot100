## 1、大体思路

这是一道非常经典的**分治法（Divide and Conquer）**题目，也是递归思维的绝佳练习。

你能“画出来”说明你已经掌握了核心逻辑，只是卡在了**如何定义递归边界**和**计算索引位置**上。我们把你脑海里“画图”的过程拆解成代码逻辑。

### 核心思路：各司其职

这道题的突破口在于理解两个数组的不同职责：

1. **先序遍历 (`preorder`)**：负责**提供“根节点”**。
   - `preorder` 的第一个元素 *永远* 是当前的根节点。
2. **中序遍历 (`inorder`)**：负责**划分“左右子树”**。
   - 一旦在 `inorder` 中找到了根节点的位置，由于中序是 `[左, 根, 右]`，那么根节点左边的所有数就是左子树，右边的就是右子树。

------

### 从“画图”到“代码”的转换

假设：

```
preorder = [3, 9, 20, 15, 7]
inorder = [9, 3, 15, 20, 7]
```

**Step 1: 找根节点**

- **动作**：看 `preorder[0]`，是 `3`。
- **代码**：`rootVal = preorder[preStart]`。

**Step 2: 切分左右地盘**

- **动作**：在 `inorder` 里找到 `3` 的位置（索引为 1）。
- **代码**：我们需要一个 HashMap 提前存好 `inorder` 的 `值 -> 索引` 映射，这样能 $O(1)$ 找到。假设位置是 `index = 1`。

**Step 3: 计算左子树有多大（关键点）**

- **动作**：在 `inorder` 里，`3` 的左边只有 `[9]`，长度是 1。
- **代码**：`leftSize = index - inStart`。

**Step 4: 递归构建**

这里是最容易晕的地方，就是**如何确定数组的下标范围**。

- **左子树范围**：
  - `preorder`: 从 `根节点 + 1` 开始，长度为 `leftSize`。
  - `inorder`: 从 `当前起点` 到 `根节点索引 - 1`。
- **右子树范围**：
  - `preorder`: 跳过根节点和左子树（`根节点 + leftSize + 1`）开始，直到结束。
  - `inorder`: 从 `根节点索引 + 1` 到 `当前终点`。

------

### Java 代码实现

结合你常用的 Java 语言，我写了一个带有详细注释的版本。

Java

```
import java.util.HashMap;
import java.util.Map;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    // 为了快速定位 inorder 中的根节点位置，避免每次都遍历，使用哈希表
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        // 预处理 inorder，将 值 -> 索引 存入 Map
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        // 开启递归
        return build(preorder, 0, preorder.length - 1, 
                     inorder, 0, inorder.length - 1);
    }

    /**
     * @param preorder 先序数组
     * @param preStart 先序数组的当前子树 起始下标
     * @param preEnd   先序数组的当前子树 结束下标
     * @param inorder  中序数组
     * @param inStart  中序数组的当前子树 起始下标
     * @param inEnd    中序数组的当前子树 结束下标
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd, 
                           int[] inorder, int inStart, int inEnd) {
        
        // 1. 递归终止条件：如果范围无效（起始点大于结束点），说明是空节点
        if (preStart > preEnd) {
            return null;
        }

        // 2. 拿到根节点的值（先序遍历的第一个就是根）
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // 3. 在中序遍历中找到根节点的位置
        int rootIndex = indexMap.get(rootVal);

        // 4. 核心：计算左子树的节点数量
        // rootIndex 是根的位置，inStart 是当前范围起点，中间的差值就是左子树的个数
        int leftSize = rootIndex - inStart;

        // 5. 递归构建左右子树（最容易写错下标的地方）
        
        // 构建左子树：
        // preorder范围：[起点+1, 起点+左子树长度]
        // inorder范围： [起点, 根位置-1]
        root.left = build(preorder, preStart + 1, preStart + leftSize, 
                          inorder, inStart, rootIndex - 1);

        // 构建右子树：
        // preorder范围：[起点+左子树长度+1, 终点]
        // inorder范围： [根位置+1, 终点]
        root.right = build(preorder, preStart + leftSize + 1, preEnd, 
                           inorder, rootIndex + 1, inEnd);

        return root;
    }
}
```

### 为什么你之前“写不出来”？

通常是因为陷入了具体的**数组切片**细节。在写这种算法时，不要去想“创建新的子数组”（那样空间复杂度很高），而是**利用下标指针**（`start`, `end`）在原数组上“圈地盘”。

**记住这个公式：**

$$\text{leftSize} = \text{rootIndex} - \text{inStart}$$

这是连接 `preorder` 和 `inorder` 下标体系的唯一桥梁。

### 下一步建议

这道题弄懂后，你可以尝试不做任何修改，直接做 **LeetCode 106. 从中序与后序遍历序列构造二叉树**。

逻辑完全一样，唯一的区别是：后序遍历的根节点在**最后**。你要不要试着写一下那一题的思路？

## 2、如何划分左右子树区间？

这确实是整道题最让人头晕的地方。别急，我们把数组看作**积木块**，不要去死记硬背 `+1` 还是 `-1`，而是理解**长度**和**偏移量**。

最核心的一把“钥匙”是：**左子树在 `preorder` 和 `inorder` 中的节点数量是一定相等的。**

只要算出左子树有几个节点（`leftSize`），我们就能拿这个数字去 `preorder` 数组里“圈”地盘。

------

### 图解：两张地图，一种地形

假设我们现在的任务范围如下：

- **preorder** 范围：`[preStart, preEnd]`
- **inorder** 范围： `[inStart, inEnd]`

#### 第一步：在中序里找“锚点”

我们在 `preorder` 拿到了根节点 `Root`（也就是 `preorder[preStart]`）。

然后在 `inorder` 里找到了它的位置 `rootIndex`。

此时，`inorder` 被切成了三段：

```
[ 左子树的地盘 ... | Root | ... 右子树的地盘 ]
^ inStart           ^ rootIndex           ^ inEnd
```

#### 第二步：计算核心变量 `leftSize`

这是最关键的一步。请看 `inorder` 数组：

- 左子树是从 `inStart` 开始，一直到 `rootIndex` 前面那个位置。
- **左子树的长度 `leftSize` = `rootIndex` - `inStart`**

> **为什么要算这个长度？**
>
> 因为 `preorder` 数组是乱序的（根-左-右），我们不知道左子树在哪结束。但是，**左子树的长度在两个数组里是一样的！** 我们知道了长度，就能在 `preorder` 里把左子树那一段精准地画出来。

------

### 第三步：划分地盘（详细推导）

现在我们手里有了 `leftSize`，我们来给左右子树分配下标。

#### 1. 左子树 (Left Subtree)

**Inorder (中序) 范围：** 很直观，根节点的左边。

- 起点：`inStart`
- 终点：`rootIndex - 1`

**Preorder (先序) 范围：** 根节点后面紧接着就是左子树。

- 起点：`preStart + 1` （跳过当前的根节点）
- 终点：`preStart + leftSize` （起点 + 长度，这就是为什么我们要算 `leftSize`）

#### 2. 右子树 (Right Subtree)

**Inorder (中序) 范围：** 很直观，根节点的右边。

- 起点：`rootIndex + 1`
- 终点：`inEnd`

**Preorder (先序) 范围：** 左子树后面紧接着就是右子树。

- 起点：`preStart + leftSize + 1` （跳过根节点，再跳过整个左子树，剩下的就是右子树的开始）
- 终点：`preEnd` （一直到当前范围结束）

------

### 代入数字实战一下

假设：

```
preorder = [3, 9, 20, 15, 7]
inorder  = [9, 3, 15, 20, 7]
```

当前处理：`preStart=0`, `preEnd=4`, `inStart=0`, `inEnd=4`

1. **找根：** `rootVal` = `3`。
2. **找位置：** 在 `inorder` 里，`3` 的下标 `rootIndex` = **1**。
3. **算长度：** `leftSize` = `rootIndex` - `inStart` = 1 - 0 = **1**。（说明左子树只有1个节点）。

**现在开始划分：**

**左子树递归：**

- **Inorder:** `[0, 1-1]` -> `[0, 0]` (即数字 `[9]`)
- **Preorder:** 起点是 `0+1`，终点是 `0+1` -> `[1, 1]` (即数字 `[9]`)
  - *看见了吗？我们在 preorder 里成功圈出了 `[9]` 这一块。*

**右子树递归：**

- **Inorder:** `[1+1, 4]` -> `[2, 4]` (即数字 `[15, 20, 7]`)
- **Preorder:** 起点是 `0+1+1` (跳过根3，跳过左9)，即从下标 2 开始，到 4 -> `[2, 4]` (即数字 `[20, 15, 7]`)
  - *精准地把右边的积木块也传下去了。*

### 总结公式（建议直接写在纸上对照）

写代码的时候，最容易错的就是 **Preorder 的右子树起点**。你只需要记住：

**“右子树的起点” = “当前起点” + “1个根节点” + “左子树的长度”**

Java

```
// 核心变量
int leftSize = rootIndex - inStart;

// 左子树
build(
    preorder, 
    preStart + 1,             // pre 起点：跳过根
    preStart + leftSize,      // pre 终点：起点 + 长度
    inorder, 
    inStart,                  // in 起点
    rootIndex - 1             // in 终点：根左边
);

// 右子树
build(
    preorder, 
    preStart + leftSize + 1,  // pre 起点：跳过根 + 跳过左子树
    preEnd,                   // pre 终点
    inorder, 
    rootIndex + 1,            // in 起点：根右边
    inEnd                     // in 终点
);
```

现在这个划分逻辑清晰了吗？你可以试着拿那张纸，把 `leftSize` 标注在两个数组中间，作为连接它们的桥梁。