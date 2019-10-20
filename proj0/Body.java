import java.lang.Math;

public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double GPar = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow((b.xxPos-this.xxPos),2) + Math.pow((b.yyPos - this.yyPos),2));
	}

	public double calcForceExertedBy(Body b) {
		return GPar*this.mass*b.mass/Math.pow(this.calcDistance(b),2);
	}

	public double calcForceExertedByX(Body b) {
		return calcForceExertedBy(b)*(b.xxPos-this.xxPos)/calcDistance(b);
	}

	public double calcForceExertedByY(Body b) {
		return calcForceExertedBy(b)*(b.yyPos-this.yyPos)/calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] b) {
		double sum = 0;
		for(int i=0; i< b.length; i++) {
			if(!this.equals(b[i])) {
				sum += calcForceExertedByX(b[i]);
			}	
		}
		return sum;
	}

	public double calcNetForceExertedByY(Body[] b) {
		double sum = 0;
		for(int i=0; i< b.length; i++) {
			if(!this.equals(b[i])) {
				sum += calcForceExertedByY(b[i]);
			}	
		}
		return sum;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX/mass;
		double aY = fY/mass;

		xxVel += dt * aX;
		yyVel += dt * aY;

		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}