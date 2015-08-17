/**
 * All the rights are reserved to Sree & Soor inc. :)
 *
 *
 */
package com.assignment.map.elements;

/**
 * @author Srilakshmy Krishnan
 * @since 2015/08/15
 */

/** Node is an interface is a group of related methods defined in Map elements.
 *
 */
public interface Node
{
    char solValue();
    int getNodeCost();
    int getxValue();
    int getyValue();
    char getNodeElement();
    String getnodeName();
    boolean isWalkable();
    void setGCost(double gCost);
    void setHCost(double hCost);
    double getGCost();
    double getHCost();
    void setPreviousNode(Node currentNode);
    Node getPreviousNode();
}
