package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


public class TiltGame extends ApplicationAdapter {
	SpriteBatch batch;
	private BitmapFont font;
	private Texture texture;
	private Sprite sprite;
	Texture img;
	World world;
	Box2DDebugRenderer debugRenderer;

	private String message = "Do something already!";
	private float highestY = 0.0f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("ball.png"));
		sprite = new Sprite(texture);
		sprite.setPosition(400, 600);
		font = new BitmapFont();
		font.setColor(Color.RED);
		//Box2D.init();
		//World world = new World(new Vector2(0, -10), true);
		//Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render() {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//font.draw(batch, "Hello World", 500, 500);
		int deviceAngle = Gdx.input.getRotation();
		//Orientation orientation = Gdx.input.getNativeOrientation();
		float accelY = Gdx.input.getAccelerometerY();
		if(accelY > highestY)
			highestY = accelY;
		message = "Device rotated to:" + Integer.toString(deviceAngle) + " degrees\n";
		message += "Device orientation is ";

		message += "Device Resolution: " + Integer.toString(w) + "," + Integer.toString(h) + "\n";
		message += "Y axis accel: " + Float.toString(accelY) + " \n";
		message += "Highest Y value: " + Float.toString(highestY) + " \n";

		if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Compass)){
			message += "Azmuth:" + Float.toString(Gdx.input.getAzimuth()) + "\n";
			message += "Pitch:" + Float.toString(Gdx.input.getPitch()+90) + "\n";
			message += "Roll:" + Float.toString(Gdx.input.getRoll()) + "\n";
		}
		else{
			message += "No compass available\n";
		}
		float x = sprite.getX();
		float y = sprite.getY();
		float roll = Gdx.input.getRoll();
		float pitch = Gdx.input.getPitch();
		sprite.setPosition(x + roll, y + pitch);
		message += "Sprite posX:" + Float.toString(x) + " " + "Sprite posY: " + Float.toString(y);
		font.draw(batch, message, 0, h);
		sprite.draw(batch);
		batch.end();
		//world.step(1 / 60f, 6, 2);
		//debugRenderer.render(world, camera.combined);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
