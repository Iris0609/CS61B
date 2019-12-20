import java.util.*;
public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        int numPlanet = in.readInt();
        double radius = in.readDouble();
        return radius;
    };
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int numPlanet = in.readInt();
        Planet[] allPlanets = new Planet[numPlanet];
        double radius = in.readDouble();
        for(int i = 0; i<numPlanet; i++){
            Planet P = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
            allPlanets[i] = P;

        };
        return allPlanets;
    };
    public static void main(String[] args){
        double T, dt, radius;
        String filename;
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];
        radius = readRadius(filename);
        In in  = new In(filename);
        int numPlanet = in.readInt();
        Planet[] allPlanets = new Planet[numPlanet];
        /**Draw universe*/
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        allPlanets = readPlanets(filename);
//        for(int i = 0; i<allPlanets.length; i++){
//            allPlanets[i].draw();
//        };
//        StdDraw.show();

        StdDraw.enableDoubleBuffering();
        for(int t = 0; t<T; t+=dt){
            double[] xForces = new double[numPlanet];
            double[] yForces = new double[numPlanet];
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i = 0; i<allPlanets.length;i++){
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            };
            for(int i = 0; i<allPlanets.length;i++){
                allPlanets[i].update(dt,xForces[i],yForces[i]);
                allPlanets[i].draw();
            };
            StdDraw.show();
            StdDraw.pause(10);

        };

        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        };

    };
};