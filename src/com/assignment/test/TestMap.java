/**
 * All the rights are reserved to Sree & Soor inc. :)
 *
 *
 */
package com.assignment.test;


/**
 * @author Srilakshmy Krishnan
 * @since 2015/08/15
 */
/* Importing java packages*/
import com.assignment.algorithm.PathFinding;
import com.assignment.map.Map;
import java.io.IOException;

/** Class TestMap is the main class method. The class creates a object of the class Map and calls two of its functions createMap
 *  and printMap.
 *  The class creates an object of the class PathFinding and calls its method aStar with three parameters namely getNodeElements
 *  getStart and getFinish.
 */
public class TestMap
{
    public static void main(String[] args) throws IOException
    {
        Map map= new Map();
        map.createMap();
        map.printMap();
        PathFinding pathFinding= new PathFinding();
        pathFinding.aStar(map.getNodeElements(), map.getStart(), map.getFinish());
        pathFinding.printPath();
    }
}
