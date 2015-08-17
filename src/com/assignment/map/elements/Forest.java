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

/** Map element Forest creates a constructor with parameters x and y and sets the row of the map xValue and column as yValue.
 *  Defines all the variables used in the methods.
 */
public class Forest implements Node
{
    private int xValue;
    private int yValue;
    private double gCostValue;
    private double hCostValue;
    private Node previousNode;
    public Forest(int x,int y)
    {
        this.xValue=x;
        this.yValue=y;
    }
    /** Method getxValue is used to return the row value of the map element to be referenced in class Map.
     * @return [row value]
     */
    @Override
    public int getxValue(){
        return xValue;
    }
    /** Method getyValue is used to return the column value of the map element to be referenced in class Map.
     * @return [col value]
     */
    @Override
    public int getyValue(){
        return yValue;
    }
    /** Method getNodeElements is used to return the representation of each map elements. Say Forest as F.
     * @return F
     */
    @Override
    public char getNodeElement()
    {
        return 'F';
    }
    /** Method getnodeName is used to return the representation of each map elements. Say Forest as FOREST.
     * @return FOREST
     */
    @Override
    public String getnodeName()
    {
        return "FOREST";
    }
    /** Method isWalkable is a boolean return type method which returns true stating that Start is a walkable node for its check in class Map.
     * @return true
     */
    @Override
    public boolean isWalkable()
    {
        return true;
    }
    /** Method setGCost method sets the value of G cost with the received parameter of type double.
     * @return sets gCostValue
     */
    @Override
    public void setGCost(double gCost)
    {
        this.gCostValue=gCost;
    }
    /** Method setHCost method sets the value of H cost with the received parameter of type double.
     * @return sets hCostValue
     */
    @Override
    public void setHCost(double hCost)
    {
        this.hCostValue = hCost;
    }
    /** Method getGCost method gets the value of G cost.
     * @return gCostValue.
     */
    @Override
    public double getGCost()
    {
        return gCostValue;
    }
    /** Method getHCost method gets the value of H cost.
     * @return hCostValue
     */
    @Override
    public double getHCost()
    {
        return hCostValue;
    }
    /** Method setPreviousNode sets the parent of the current node.
     * @return sets previousNode.
     */
    @Override
    public void setPreviousNode(Node currentNode)
    {
        this.previousNode=currentNode;
    }
    /** Method getPreviousNode gets the parent node.
     * @return previousNode.
     */
    @Override
    public Node getPreviousNode()
    {
        return previousNode;
    }

    /** Method solValue returns '#' character for the final output pattern printing.
     * @return #
     */
    @Override
    public char solValue()
    {
        return '#';
    }
    /** Method getNodeCost return 1 as the value for class Start
     * @return 1
     */
    @Override
    public int getNodeCost()
    {
        return 2;
    }
}

