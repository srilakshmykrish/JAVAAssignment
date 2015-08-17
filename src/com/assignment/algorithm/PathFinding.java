/**
 * All the rights are reserved to Sree & Soor inc. :)
 *
 *
 */

package com.assignment.algorithm;

import com.assignment.map.elements.Node;

import java.lang.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Srilakshmy Krishnan
 * @since 2015/08/15
 */

/** class PathFinding implements the Astar algorithm and calculate the cost of traversal and gives the optimal
 *  path of the given map.
 *  Creates an array of nodes namely non_visitedArray and visitedArray
 */
public class PathFinding
{
    /** non_visited array refers to the nodes that are yet to be traversed.
     *  visited array is already traversed nodes
     *  optimumPath is array which stores the least cost traversal nodes based on cost estimation
    */
    ArrayList<Node> non_visitedArray = new ArrayList<Node>();
    ArrayList<Node> visitedArray = new ArrayList<Node>();
    ArrayList<Node> optimumPath = new ArrayList<Node>();

    /*  Method aStar which accepts the parameter mapElements, start and finish from class Map
    *   and sets the current node as start and traverse the loop until finish node is seen. For each
    *   current node it finds all its adjacent nodes.
    */

    public Node aStar(Node[][] mapElements, Node start, Node finish)
    {
        Node currentNode = start;
        while (currentNode != finish)
        {
            int row = currentNode.getxValue();
            int col = currentNode.getyValue();
            //adjacent right child
            col++;
            addNode(row, col, mapElements, currentNode, finish);

            //adjacent left child
            col -= 2;
            addNode(row, col, mapElements, currentNode, finish);

            //top child
            col++;
            row--;
            addNode(row, col, mapElements, currentNode, finish);

            //bottom child
            row += 2;
            addNode(row, col, mapElements, currentNode, finish);

            //bottom right
            col++;
            addNode(row, col, mapElements, currentNode, finish);

            //bottom left
            col -= 2;
            addNode(row, col, mapElements, currentNode, finish);

            //top left
            row -= 2;
            addNode(row, col, mapElements, currentNode, finish);

            //top right
            col += 2;
            addNode(row, col, mapElements, currentNode, finish);
            visitedArray.add(currentNode);
            Collections.sort(non_visitedArray, sortNodes );
            currentNode = non_visitedArray.remove(non_visitedArray.size() - 1);
        }
        Node tmp = currentNode;
        while(tmp != null)
        {
            optimumPath.add(0, tmp);
            tmp=tmp.getPreviousNode();
        }
        return currentNode;
    }

    /**
     *
     * @param row
     * @param col
     * @param mapElements
     * @param currentNode
     * @param finish
     */
    private void addNode(int row,int col, Node[][] mapElements, Node currentNode, Node finish)
    {
        if ((row >= 0 && col >= 0) && (row <= 49 && col <= 49))
        {
            if(mapElements[row][col].isWalkable())
            {
                double g = currentNode.getGCost() + getExactCost(row, col, currentNode);
                double f = g + getHeuristicCost(row,col,finish);
                if(!isNodeVisited(row, col))
                {
                    Node nextNode = getnextNodeFromNonVisitedArray(row, col);
                    if (nextNode == null)
                    {
                        nextNode = mapElements[row][col];
                        nextNode.setGCost(g);
                        nextNode.setHCost(f);
                        nextNode.setPreviousNode(currentNode);
                        non_visitedArray.add(nextNode);
                    }
                    else if(nextNode.getGCost()>g)
                    {
                        nextNode.setGCost(g);
                        nextNode.setHCost(f);
                        nextNode.setPreviousNode(currentNode);                    }

                }
                else if (mapElements[row][col].getGCost() > g)
                {
                    mapElements[row][col].setGCost(g);
                    mapElements[row][col].setPreviousNode(currentNode);
                }
            }
        }
    }
    public boolean isNodeVisited(int row, int col)
    {
        for (int i = 0; i < visitedArray.size(); ++i)
        {
            if ((visitedArray.get(i).getyValue() == col) && (visitedArray.get(i).getxValue() == row))
            {
                return true;
            }
        }
        return false;

    }
    private Node getnextNodeFromNonVisitedArray(int row, int col)
    {
        for (int i = 0; i < non_visitedArray.size(); ++i)
        {
            if ((non_visitedArray.get(i).getyValue() == col) && (non_visitedArray.get(i).getxValue() == row))
            {
                return non_visitedArray.get(i);
            }
        }
        return null;
    }
    public double getExactCost(int row, int col, Node finish)
    {
        return Math.sqrt((row - finish.getxValue()) * (row - finish.getxValue()) + (col - finish.getyValue()) * (col - finish.getyValue()));
    }

    public double getHeuristicCost(int row, int col, Node currentNode)
    {
        return Math.abs(currentNode.getxValue() - row) + Math.abs(currentNode.getyValue() - col);
        //return Math.sqrt((currentNode.getxValue() - row) * (currentNode.getxValue() - row) + (currentNode.getyValue() - col) * (currentNode.getyValue() - col));
    }
    public void printPath()
    {
        System.out.println("======================================   NODE   TRAVERSAL   FROM   START   TO   FINISH   ============================================================ ");
        int totalCost=0;
        char[][] mapSolution = new char[50][50];
        for(int i=0;i<50;i++)
        {
            Arrays.fill(mapSolution[i],'.');
        }
        for(int i=0;i< optimumPath.size();i++)
        {
            Node node = optimumPath.get(i);
            mapSolution[node.getxValue()][node.getyValue()]=node.solValue();
            totalCost=totalCost+node.getNodeCost();
            System.out.println(+i +" Node chooses " +node.getnodeName() + " located in [" +node.getxValue() + "]"+"["+node.getyValue()+"]");
        }
        System.out.println(" Final Map Solution ");
        for(int i=0;i<50;i++)
        {
            System.out.print(i);
            for(int j=0;j<50;j++)
            {
                System.out.print(mapSolution[i][j]);
            }
            System.out.println();
        }
        System.out.println("The total cost estimation is " +totalCost);
    }
    public static Comparator<Node> sortNodes = new Comparator<Node>()
    {
        public int compare(Node n0, Node n1)
        {
            if (n1.getHCost() > n0.getHCost())
            {
                return 1;
            }
            else if (n1.getHCost() < n0.getHCost())
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }
            ;
}


