package com.huai.leetcode;

import java.util.*;

public class No95_UniqueBinarySearchTreesII {


    public List<TreeNode> generateTrees(int n) {
        if(n < 1) throw new IllegalArgumentException();

        Set<Integer> existNums = new HashSet<>();

       TreeNode root = null;
        for(int i = 1; i <= n; i++){
            generate(i, n, root, existNums);
        }

        return null;
    }

    private void generate(int curr, int n, TreeNode node, Set<Integer> existNums){
        if(existNums.size() == n) return ;
        if(existNums.contains(curr)) return ;
        if(node == null) node = new TreeNode(curr);
        if(curr < node.val){

            node.left = new TreeNode(curr);

        }else if(curr > node.val){
            node.right = new TreeNode(curr);
        }
    }
}

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
