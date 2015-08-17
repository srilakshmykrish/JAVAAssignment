/**
 * All the rights are reserved to Sree & Soor inc. :)
 *
 *
 */
package com.assignment.map;
import com.assignment.map.elements.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * @author Srilakshmy Krishnan
 * @since 2015/08/15
 */

/** Class Map reads the source file large_map.txt in the resource folder and perform
 * createMap and printMap methods.
 */
public class Map
{
    private Node[][] mapElements = new Node[50][50];
    private Node start;
    private Node finish;

    /** createMap reads the file and represent the source file in terms of node names. Say @ as S etc
     */
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
                            mapElements[i][j] = new Start(i, j);
                            start= mapElements[i][j];
                            break;
                        // The character '.' represents mode of path as FlatLands.
                        case '.':
                            mapElements[i][j] = new FlatLands(i, j);
                            break;
                        // The character '*' represents mode of path as Forest.
                        case '*':
                            mapElements[i][j] = new Forest(i, j);
                            break;
                        // The character '^' represents mode of path as Mountains.
                        case '^':
                            mapElements[i][j] = new Mountain(i, j);
                            break;
                        // The character '~' represents mode of path as Water.
                        case '~':
                            mapElements[i][j] = new Water(i, j);
                            break;
                        // The character 'X' represents the Finish Node.
                        case 'X':
                            mapElements[i][j] = new Finish(i, j);
                            finish= mapElements[i][j];
                            break;
                        default:
                            System.out.println("Invalid character");
                    }
                }
            }
        }
        catch (IOException exception)
        {
            System.out.println("Error processing file: " + exception);
        }
    }

    /** printMap Map using node names as each entity. Prints @ as Start element 'S'.
     */
    public void printMap()
    {
        for (int i = 0; i < 50; i++)
        {
            for (int j = 0; j < 50; j++)
            {
                System.out.print(mapElements[i][j].getNodeElement() + " ");
            }
            System.out.println();
        }
    }

    /** getNodeElements method returns parameter for class PathFinding
     * @return mapElements
     */
    public Node[][] getNodeElements()
    {
        return mapElements;
    }
    / /** getNodeElements method returns parameter for class PathFinding
 * @return start
 */
    public Node getStart()
    {
        return start;
    }
    /** getNodeElements method returns parameter for class PathFinding
     * @return finish
     */
    public Node getFinish()
    {
        return finish;
    }
}
