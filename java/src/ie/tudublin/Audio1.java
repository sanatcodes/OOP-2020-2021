package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {

    Minim minim; // Connect to minim
    AudioInput ai; // How to connect to mic
    AudioPlayer ap;
    AudioBuffer ab; // Samples

    float[] lerpBuffer;

    public void settings()
    {
        size(512, 512);
        // fullScreen(P3D, SPAN); // Try this for full screen multiple monitor support :-) Be careful of exceptions!
    }

    float y = 300;
    float lerpedY = y;


    public void setup()
    {
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        //ap = minim.loadFile("heroplanet.mp3", width);
        //ab = ap.mix; // Connect the buffer to the mp3 file
        ab = ai.mix; 
        colorMode(HSB);
        lerpBuffer = new float[width];

    }

    float lerpedAverage;

    public void draw()
    {
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;
        for(int i = 0 ; i < ab.size(); i ++)
        {
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
            lerpBuffer[i] = lerp(lerpBuffer[i], ab.get(i),0.1f);

            line(i, halfHeight - lerpBuffer[i] * halfHeight, halfHeight + lerpBuffer[i] * halfHeight, i);
            sum = (abs(ab.get(i)) + sum);
            
            //println(ab.get(i));
        }
        // calculate the average amplitude
        average = sum / (float)ab.size();
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);
        //lerped eclipse
        // ellipse(width / 2, 100, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
        // Non lerped eclipse
        // ellipse(width / 4, 100, average * 500, average * 500);

        // ellipse(200, y, 30, 30);
        // ellipse(300, lerpedY, 30, 30);
        // y += random(-10,10);
        // lerpedY = lerp(lerpedY,y,0.1f);
        
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                }        
                break;
            }   
            case 1:
            {
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
                }        
                break;
            }
            case 2:
            {
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
                    line(0, i, lerpedBuffer[i] * halfHeight * 4, i);
                    line(width, i, width - (lerpedBuffer[i] * halfHeight * 4), i);
                    line(i, 0, i, lerpedBuffer[i] * halfHeight * 4);
                    line(i, height, i, height - (lerpedBuffer[i] * halfHeight * 4));
                }        
                break;
            }
            case 3:
            {
                float c = map(average, 0, 1, 0, 255);
                stroke(c, 255, 255);        
                strokeWeight(2);
                noFill();
                // See the difference lerping makes? It smooths out the jitteryness of average, so the visual looks smoother
                //ellipse(width / 4, 100, 50 + average * 500, 50 + average * 500);
                ellipse(width / 2, 100, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));                
                break;
            }
            case 4:
            {
                float c = map(average, 0, 1, 0, 255);
                stroke(c, 255, 255);        
                strokeWeight(2);
                noFill();
                rectMode(CENTER);
                float size = 50 + (lerpedAverage * 500);
                rect(width / 2, height / 2, size, size);
                break;
            }
            case 5:
            {
                float r = 0.1f;
                int numPoints = 20;
                float thetaInc = TWO_PI / (float) numPoints;
                strokeWeight(2);
                stroke(255);
                float lastX = width / 2, lastY = height / 2;
                for(int i = 0 ; i < 1000 ; i ++)
                {
                    float theta = i * thetaInc;
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 - cos(theta) * r;
                    r += 0.3f;
                    line(lastX, lastY, x, y);
                    lastX = x;
                    lastY = y;
                }
                // ??
                break;
            }
        }        
    }
}