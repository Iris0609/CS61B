import java.lang.*;
public class Planet{
        public double xxPos, yyPos, xxVel, yyVel, mass;
        public String imgFileName;
        static final double G = 6.67e-11;
        public Planet(double xP, double yP, double xV, double yV, double m, String img){
            this.xxPos = xP;
            this.yyPos = yP;
            this.xxVel = xV;
            this.yyVel = yV;
            this.mass = m;
            this.imgFileName = img;
        };
        public Planet(Planet P){
            this.xxPos = P.xxPos;
            this.yyPos = P.yyPos;
            this.xxVel = P.xxVel;
            this.yyVel = P.yyVel;
            this.mass = P.mass;
            this.imgFileName = P.imgFileName;

        };

        public double calcDistance(Planet P){
            double dx, dy, r;
            dx = this.xxPos - P.xxPos;
            dy = this.yyPos - P.yyPos;
            r = Math.sqrt(dx * dx + dy * dy);
            return r;

        };

        public double calcForceExertedBy(Planet P){
            double r2, F;
            r2 = Math.pow(calcDistance(P),2);
            F = G * this.mass * P.mass / r2;
            return F;

        };

        public double calcForceExertedByX(Planet P){
            double F, Fx, dx;
            dx = P.xxPos - this.xxPos;
            F = calcForceExertedBy(P);
            Fx = F * dx /calcDistance(P);
            return Fx;
        };

        public double calcForceExertedByY(Planet P){
            double F, Fy, dy;
            dy = P.yyPos - this.yyPos;
            F = calcForceExertedBy(P);
            Fy = F * dy /calcDistance(P);
            return Fy;
        };

        public double calcNetForceExertedByX(Planet[] allPlanets){
            double netX;
            netX = 0;
            for(int i = 0; i<allPlanets.length; i++){
                if (this.equals(allPlanets[i])){
                    continue;
                };
                netX += calcForceExertedByX(allPlanets[i]);

            };
            return netX;
        };
        public double calcNetForceExertedByY(Planet[] allPlanets){
            double netY;
            netY = 0;
            for(int i = 0; i<allPlanets.length; i++){
                if (this.equals(allPlanets[i])){
                    continue;
                };
                netY += calcForceExertedByY(allPlanets[i]);

            };
            return netY;
        };

        public void update(double dt, double Fx, double Fy){
            double ax, ay;
            ax = Fx / this.mass;
            ay = Fy / this.mass;
            this.xxVel += ax * dt;
            this.yyVel += ay * dt;
            this.xxPos += this.xxVel * dt;
            this.yyPos += this.yyVel *dt;

        };

        public void draw(){
            StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);

        };

        };


