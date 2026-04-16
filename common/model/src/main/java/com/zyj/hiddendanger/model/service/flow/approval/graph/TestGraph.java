package com.zyj.hiddendanger.model.service.flow.approval.graph;

public class TestGraph {
    public static void main(String[] args) {

        Integer[][] graph = new Integer[][]{
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };
        ApprovalFlowGraph approvalFlowGraph = new ApprovalFlowGraph(graph, "123", "123");
    }
}
