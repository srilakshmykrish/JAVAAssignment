/**
 * All the rights are reserved to Sree & Soor inc. :)
 *
 *
 */
package com.assignment.test;

import com.assignment.map.Map;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Srilakshmy Krishnan
 * @since 2015/08/12
 */

public class TestMap
{
    public static void main(String[] args) throws IOException
    {
        Map map= new Map();
        map.createMap();
        map.printMap();
        map.costMap();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the row of the node: ");
        int Row = input.nextInt();
        System.out.println("Enter the column of the node: ");
        int Column = input.nextInt();
        map.getAdjacent(Row,Column);
    }
}
