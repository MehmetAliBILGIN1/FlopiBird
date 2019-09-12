package com.mab.ucankuslar;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import java.util.Random;



public class UcanKuslar extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;
	Texture bird,share;
	Texture enemy1 , enemy2 , enemy3;
	float birdx,birdy,birdw,birdh; //kuşun konumu
	float screenw , screenh;       // ekran ağırlıkları
	float gravity = 0.3f;          // yer çekimi
	float v = 0.0f;                // kuşun düşme hızı
    int state=0;
    int enemycount = 3;
    float ex ,ey,ew,eh; //düşmanın konumu
	float enemyx[]= new float[enemycount];
	float d ;
	float enemyy[][] = new float[3][enemycount];
	Circle c_bird;			// hitbox
	Circle c_enemy1[] = new Circle[enemycount]; 		//	hitbox
	Circle c_enemy2[] = new Circle[enemycount];
	Circle c_enemy3[] = new Circle[enemycount];

	BitmapFont  font;
	BitmapFont  font1;
	BitmapFont  font2;

	Sound sound , sound1,sound2,sound3;
	Music music;

	Boolean flag = true;
	Boolean flag1 = true;


	int score=0;
	int hscore=0;
	ShapeRenderer sr;
	public Preferences preferences;


	@Override
	public void create () {



		batch = new SpriteBatch();
		img = new Texture("back3.png" );
		bird = new Texture("bird1.png");
		enemy1 = new Texture("enemy1.png");
		enemy2 = new Texture("enemy2.png");
		enemy3 = new Texture("enemy1.png");

        preferences = Gdx.app.getPreferences("game preferences");

		screenw = Gdx.graphics.getWidth();
		screenh = Gdx.graphics.getHeight();

		ImageButton share;

		birdx  = screenw/4;
		birdy  = screenh/4;
		birdw  = screenw/10;
		birdh  = screenh/10;
		ex = screenw/4;
		ey = screenh/4;
		ew = screenw/12;
		eh = screenh/10;

    	font1 = new BitmapFont(Gdx.files.internal("font1.fnt"),false);
		font1.setColor(Color.RED);
		font1.getData().setScale(4);

		sound = Gdx.audio.newSound(Gdx.files.internal("lose.ogg"));
		sound1 = Gdx.audio.newSound(Gdx.files.internal("sound1.ogg"));
		sound2 = Gdx.audio.newSound(Gdx.files.internal("point.ogg"));
		sound3 = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));

		//music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
		sr=new ShapeRenderer();

		c_bird = new Circle();

		c_enemy1 = new Circle[enemycount];
		c_enemy2 = new Circle[enemycount];
		c_enemy3 = new Circle[enemycount];

		d=screenw/2;


		for(int i=0; i<enemycount; i++)
		{
			enemyx[i]=screenw+i*d;
            Random r1 = new Random();
            Random r2 = new Random();
            Random r3 = new Random();

            enemyy[0][i]=r1.nextFloat()*screenh;
            enemyy[1][i]=r2.nextFloat()*screenh;
            enemyy[2][i]=r3.nextFloat()*screenh;
			c_enemy1[i] = new Circle();
			c_enemy2[i] = new Circle();
			c_enemy3[i] = new Circle();
		}
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(img , 0 , 0 , screenw , screenh);
		batch.draw(bird , birdx , birdy , birdw , birdh);


	if(state==1)
		{

			//music.setLooping(true);
			//music.play();
			flag1=true;
			if(Gdx.input.justTouched())
			{
				v=-7;
				sound3.play();
			}

			for(int i=0;i<enemycount;i++)
			{
				if(enemyx[i]<birdw)
				{
					enemyx[i] += enemycount*d;

                    Random r1 = new Random();
                    Random r2 = new Random();
                    Random r3 = new Random();

                    enemyy[0][i]=r1.nextFloat()*screenh-eh;
                    enemyy[1][i]=r2.nextFloat()*screenh-eh;
                    enemyy[2][i]=r3.nextFloat()*screenh-eh;
				}

				if(birdx>enemyx[i] && flag)
				{
					sound2.play();
					score++;
					System.out.println(score);
					flag = false;

				}

				if(enemyx[i]<birdw+6)
				{
					flag = true;
				}

 				enemyx[i] -=6;         //düsmanın hızı
				batch.draw(enemy1 , enemyx[i] , enemyy[0][i]  , ew , eh);
				batch.draw(enemy1 , enemyx[i] , enemyy[1][i]  , ew , eh);
				batch.draw(enemy1 , enemyx[i] , enemyy[2][i]  , ew , eh);

			}
			if(birdy<0)
			{
				state=2;
				birdy = screenh/3;
				v=0;
			}
			else if(birdy>screenh)
			{
				state=2;
				birdy = screenh/3;
				v=0;
			}
			else
				{
				  v=v+gravity;
				  birdy=birdy-v;
				}
        }
        else if(state==2)
        	{

				font1.draw(batch,"Game Over!!",screenw/5,birdh*8);
				font1.draw(batch,"Score:"+score,screenw/5,birdh*6);
				font1.draw(batch,"High Score:"+hscore,screenw/5,birdh*4);

				if(score>hscore)
				{
					sound1.play();
					sound.stop();
					//music.stop();
					flag1=false;
					hscore=score;

					preferences.putInteger("High Score", hscore);
					preferences.flush();
				}

				if(flag1)
				{

					//music.stop();
					sound.play();
					flag1=false;

				}

				if(Gdx.input.justTouched())
				{
					state=1;
					score=0;
					v=0;

					for(int i=0; i<enemycount; i++)
					{
						enemyx[i]=screenw+i*d;
						Random r1 = new Random();
						Random r2 = new Random();
						Random r3 = new Random();

						enemyy[0][i]=r1.nextFloat()*screenh;
						enemyy[1][i]=r2.nextFloat()*screenh;
						enemyy[2][i]=r3.nextFloat()*screenh;
						c_enemy1[i] = new Circle();
						c_enemy2[i] = new Circle();
						c_enemy3[i] = new Circle();
					}
				}
			}
			else if(state==0)
			{	flag1=true;
				hscore = preferences.getInteger("High Score");
				font1.draw(batch,"Tap To Start!",screenw/5,screenh/2);
				if(Gdx.input.justTouched())
				{
					state=1;
				}

			}
			font1.draw(batch,String.valueOf(score),screenw-birdw , birdh);

		c_bird.set(birdx+birdw/2,birdy+birdh/2,birdw/3);

		//sr.begin(ShapeRenderer.ShapeType.Filled);
		//sr.setColor(Color.BLUE);
		//sr.circle(birdx+birdw/2,birdy+birdh/2,birdw/3);

		for(int i=0;i<enemycount;i++)
		{
			//sr.circle(enemyx[i]+birdw/2,enemyy[0][i]+birdh/2,birdw/3);
			//sr.circle(enemyx[i]+birdw/2,enemyy[1][i]+birdh/2,birdw/3);
			//sr.circle(enemyx[i]+birdw/2,enemyy[2][i]+birdh/2,birdw/3);

			c_enemy1[i].set(enemyx[i]+birdw/2,enemyy[0][i]+birdh/2,birdw/3);
			c_enemy2[i].set(enemyx[i]+birdw/2,enemyy[1][i]+birdh/2,birdw/3);
			c_enemy3[i].set(enemyx[i]+birdw/2,enemyy[2][i]+birdh/2,birdw/3);

			if(Intersector.overlaps(c_bird,c_enemy1[i])|| Intersector.overlaps(c_bird,c_enemy2[i])||Intersector.overlaps(c_bird,c_enemy3[i]))
			{
				state = 2;

			}
			if(Intersector.overlaps(c_enemy1[i],c_enemy2[i])|| Intersector.overlaps(c_enemy1[i],c_enemy3[i])||Intersector.overlaps(c_enemy2[i],c_enemy3[i])||enemyy[0][i]>900 || enemyy[2][i]<0)			{

					Random r1 = new Random();
					Random r2 = new Random();
					Random r3 = new Random();

					enemyy[0][i]=r1.nextFloat()*screenh;
					enemyy[1][i]=r2.nextFloat()*screenh;
					enemyy[2][i]=r3.nextFloat()*screenh;

			}
		}

		batch.end();
		//sr.end();
		}



	@Override
	public void dispose () {

	}
}
