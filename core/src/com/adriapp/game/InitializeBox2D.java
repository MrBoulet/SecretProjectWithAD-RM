package com.adriapp.game;

import static utils.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Initialisation du core system.
 * @author Sacreairbag
 * @version 1.0.0
 */

public class InitializeBox2D extends Main {
	
	/**
	 * Instance la camera
	 */
	private OrthographicCamera camera;
	
	/**
	 *  On instance le debug d'affichage Box2D.
	 *  Permet de voir où se trouve nos entités.
	 */
	private Box2DDebugRenderer b2dr; 
	
	/**
	 * On instance notre monde.
	 */
	private World world;
	
	/**
	 * On instance une entité "player".
	 * Ainsi qu'une nommé "mur".
	 */
	private Body player, wall;
	
	/**
	 *  Initialise le monde.
	 *  Initialise le Box2DDebugRenderer.
	 */
	public InitializeBox2D() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w/2, h/2);
		
		Box2D.init(); 
		world = new World(new Vector2(0, 0), true); 
		b2dr = new Box2DDebugRenderer();
		player = createPlayer();
		wall = createWall(0, 0, 64, 32, true);
	}
	
	/**
	 * Permet de mettre à jour selon la fenêtre.
	 * Donc pas de overRendering/underRendering.
	 */
	@Override
	public void render() {
		update(Gdx.graphics.getDeltaTime());
		b2dr.render(world, camera.combined.scl(PPM));
	}
	
	/**
	 * Google it.
	 */
	@Override
	public void dispose() {
		world.dispose();
		b2dr.dispose();
	}
	
	/**
	 * Met à jour le monde.
	 * Appel cameraUpdate.
	 */
	public void update(float delta) {
		world.step(1 / 60f, 6, 2);
		
		inputUpdate(delta);
		cameraUpdate(delta);
	}
	
	public void inputUpdate(float delta) {
		int horizontalForce = 0, verticalForce = 0;
		
		if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
			verticalForce += 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			verticalForce -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			horizontalForce -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			horizontalForce += 1;
		}
		
		player.setLinearVelocity(horizontalForce * 5, verticalForce * 5);
	}
	
	/**
	 * Met à jour la camera.
	 * Camera suit le personnage.
	 * @param delta
	 */
	public void cameraUpdate(float delta) {
		Vector3 position = camera.position;
		position.x = player.getPosition().x * PPM;
		position.y = player.getPosition().y * PPM;
		camera.position.set(position);
		
		camera.update();
	}
	
	/**
	 * Créer une entité appelé "player".
	 * @return Body
	 */
	public Body createPlayer() {
		Body pBody;
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(0,0);
		def.fixedRotation = true;
		pBody = world.createBody(def);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(32 / 2 / PPM); // ça prend du centre.
		
		pBody.createFixture(shape, 1.0f);
		shape.dispose();
		return pBody;
	}
	
	public Body createWall(int x, int y, int width, int height, boolean isStatic) {
		Body pBody;
		BodyDef def = new BodyDef();
		
		if (isStatic) 
			def.type = BodyDef.BodyType.StaticBody;
		else 
			def.type = BodyDef.BodyType.DynamicBody;
		
		def.position.set(x / PPM, y / PPM);
		def.fixedRotation = true;
		pBody = world.createBody(def);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2 / PPM, height / 2 / PPM); 
		
		pBody.createFixture(shape, 1.0f);
		shape.dispose();
		return pBody;
	}
}
