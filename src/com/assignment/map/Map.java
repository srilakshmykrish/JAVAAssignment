/**
 * All the rights are reserved to Sree & Soor inc. :)
 *
 *
 */
package com.assignment.map;

import com.assignment.map.assignment.map.elements.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * @author Srilakshmy Krishnan
 * @since 2015/08/12
 */

/** Class Map reads the input file "large_map.txt" in the resource repository and converts each line
 *  as character by character and stores it in an array nodeElements.
 */
public class Map
{
    private INode[][] node = new INode[50][50];
    public int k;
    public void createMap()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("resources/large_map.txt"));
            for (int i = 0; i < 50; i++)
            {
                String line = br.readLine();
                char[] nodeElements = line.toCharArray();
                for (int j = 0; j < nodeElements.length; j++)
                {
                    switch (nodeElements[j])
                    {
                        // The character '@' represents the Starting Node.
                        case '@':
                            node[i][j] = new Start();
                            break;
                        // The character '.' represents mode of path as Flatlands.
                        case '.':
                            node[i][j] = new FlatLands();
                            break;
                        // The character '*' represents mode of path as Forest.
                        case '*':
                            node[i][j] = new Forest();
                            break;
                        // The character '^' represents mode of path as Mountains.
                        case '^':
                            node[i][j] = new Mountain();
                            break;
                        // The character '~' represents mode of path as Water.
                        case '~':
                            node[i][j] = new Water();
                            break;
                        // The character 'X' represents the Finish Node.
                        case 'X':
                            node[i][j] = new Finish();
                            break;
                    }
                }
            }
        }
        catch (IOException exception)
        {
            System.out.println("Error processing file: " + exception);
        }
    }
/* printMap is a method to convert large_map.txt to a character representation say Flatlands as L (lands)
*  Forest as F, Water as W etc.
*/
    public void printMap()
    {
        System.out.println("The Node representation of the Map is :");
        for(k=0;k<3;k++)
        {
            System.out.println();
        }
        for (int i = 0; i < 50 ; i++)
        {
            for (int j = 0; j < 50 ; j++)
            {
                System.out.print(node[i][j].getName() + " ");
            }
            System.out.println();
        }
        for(k=0;k<3;k++)
        {
            System.out.println();
        }
    }
    /* costMap is a method to get the cost of traversal for each node based on their mode of path eg. FlatLands has
    * a cost of 1, Forest has a cost of 2 etc.
    */
    public void costMap()
    {
        System.out.println("The weighted cost of the Map is :");
        for(k=0;k<3;k++)
        {
            System.out.println();
        }
        for (int i = 0; i < 50 ; i++)
        {
            for (int j = 0; j < 50 ; j++)
            {
                System.out.print(node[i][j].nodeValue() + " ");
            }
            System.out.println();
        }
        for(k=0;k<3;k++)
        {
            System.out.println();
        }
    }

    /** getAdjacent is a method defined to give all the adjacent nodes of a specific node located in the location. Any
     *  node is referenced as node[row][coloumn].
     * @param x is the row of the requested node
     * @param y is the coloumn of the requested node
     *  Also with the adjacent nodes their weighted value or cost is also calculated.
     */
    public void getAdjacent(int x,int y)
    {
        System.out.println("The adjacent nodes of the Node located at row " + x + "and coloumn " + y  + " is :");
        for(int i=x-1;i<=x+1;i++)
        {
            if (i<0 || i>49)
            {
                continue;
            }
            for(int j=y-1;j<=y+1;j++)
            {
                if (j<0 || j>49)
                {
                    continue;
                }
                System.out.print( " " + node[i][j].getName());
            }
            System.out.println();
        }
        System.out.println("The adjacent cost of Nodes located at row " + x + "and coloumn " + y  + " is :");
        for(int i=x-1;i<=x+1;i++)
        {
            if (i<0 || i>49)
            {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++)
            {
                if (j<0 || j>49)
                {
                    continue;
                }
                System.out.print(node[i][j].nodeValue() + " ");
            }
            System.out.println();
        }
    }
}
