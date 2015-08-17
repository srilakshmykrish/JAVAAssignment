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
    private Node[] node1;

    /*  Method aStar which accepts the parameter mapElements, start and finish from class Map
    *   and sets the current node as start and traverse the loop until finish node is seen. For each
    *   current node it finds all its adjacent nodes.
    */

    public Node aStar(Node[][] mapElements, Node start, Node finish)
    {
        Node currentNode = start;
        while (currentNode != finish)
        {
            // Inputs the current node's row and col by calling the method getxValue and getyValue.
            int row = currentNode.getxValue();
            int col = currentNode.getyValue();
            // adjacent nodes are listed and added.
            for (int i = row - 1; i <= row + 1; i++)
            {
                for (int j = col - 1; j <= col + 1; j++)
                {
                    if ((i == row && j == col) || i < 0 || j < 0 || j >=50 || i >= 50)
                    {
                        continue;
                    }
                    addNode(i, j, mapElements, currentNode, finish);
                }
            }
            visitedArray.add(currentNode);
            Collections.sort(non_visitedArray, sortNodes );
            currentNode = non_visitedArray.remove(non_visitedArray.size()-1);
      }
        // sets the current node to an array tmp and uses it for printing the node using optimum path
        Node tmp = currentNode;
        while(tmp != null)
        {
            optimumPath.add(0, tmp);
            tmp=tmp.getPreviousNode();
        }
        return currentNode;
    }
    /** addNode methods checks if our current map element is walkable or not using the input row and column.only element that is not walkable is map
     * element water.
     * @param row row of the node through getxValue
     * @param col col of the node through getyValue
     * @param mapElements map elements through getNodeElements
     * @param currentNode starts with the node start
     * @param finish end node
     */
    private void addNode(int row,int col, Node[][] mapElements, Node currentNode, Node finish)
    {
        if ((row >= 0 && col >= 0) && (row <= 49 && col <= 49))
        {
            if(mapElements[row][col].isWalkable())
            {
                // g and h value is set and the total cost is calculated as f=g+h.
                double g = currentNode.getGCost() + getExactCost(row, col, currentNode);
                double f = g + getHeuristicCost(row,col,finish);
                //once the node is not visited it sets the g cost and f cost of the child(next) node from map elements and adds our current node to
                // visited list.
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
                    //compares g value of current node and child node and updates the lower g value.
                    else if(nextNode.getGCost()>g)
                    {
                        nextNode.setGCost(g);
                        nextNode.setHCost(f);
                        nextNode.setPreviousNode(currentNode);                    }

                }
                //compares g value of current node and that of map element and updates the lower g value.
                else if (mapElements[row][col].getGCost() > g)
                {
                    mapElements[row][col].setGCost(g);
                    mapElements[row][col].setPreviousNode(currentNode);
                }
            }
        }
    }
    /** is NodeVisited checks if the particular node located in row and col is visited or not. returns true if visited
     * @param row row of the node
     * @param col col of the node
     * @return true/false.
     */
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

    /** the method returns the next non visited array node.
     * @param row row of the node
     * @param col col of the node
     * @return next node of the non visited array.
     */
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
    /** getExactCost calculates the cost to reach the destination from here.
     * @param row row of the node
     * @param col col of the node
     * @param finish final node finish
     * @return the cost
     */
    public double getExactCost(int row, int col, Node finish)
    {
        return Math.sqrt((row - finish.getxValue()) * (row - finish.getxValue()) + (col - finish.getyValue()) * (col - finish.getyValue()));
    }
    /** getHeuristicCost calculates the exact cost to reach this node from the starting node.
     * @param row row of the node
     * @param col col of the node
     * @param currentNode current node
     * @return the cost
     */
    public double getHeuristicCost(int row, int col, Node currentNode)
    {
        //Manhattan distance calculation
        return Math.abs(currentNode.getxValue() - row) + Math.abs(currentNode.getyValue() - col);
        //EUcledian distance calculation
        // return Math.sqrt((currentNode.getxValue() - row) * (currentNode.getxValue() - row) + (currentNode.getyValue() - col) * (currentNode.getyValue() - col));
    }

    /** printPath prints the final map after calculating the optimal path based on the manhattan distance calculation
     */
    public void printPath()
    {
        System.out.println("======================================   NODE   TRAVERSAL   FROM   START   TO   FINISH   ============================================================ ");
        int totalCost=0;
        char[][] mapSolution = new char[50][50];
        for(int i=0;i<50;i++)
        {
            Arrays.fill(mapSolution[i],'-');
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

    /** Comparator method is used to sort the nodes.
     */
    public static Comparator<Node> sortNodes = new Comparator<Node>()
    {
        /** compare method compares the nodes n0 and n1 and return 1 if the HCost of n0 is less than n1.
         * It sorts the nodes based on the lower value of HCost
         * @param n0 node
         * @param n1 node
         * @return 1/-1/0
         */
        public int compare(Node n0, Node n1)
        {
            if (n0.getHCost()< n1.getHCost())
            {
                return 1;
            }
            else if (n0.getHCost() > n1.getHCost())
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


