package com.game.megaman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

  MegamanCharacter megamano;
  int centro;
  final Megaman game;
  TmxMapLoader maploader;
  TiledMap map ;
  OrthographicCamera camera;
  OrthogonalTiledMapRenderer renderer;
  Music musica;
  GameScreen(final Megaman gam){
    game = gam;
    centro = (Gdx.graphics.getWidth())/2;
    megamano = new MegamanCharacter();
    camera = new OrthographicCamera(800, 480);
    camera.position.set(camera.viewportWidth /2f, camera.viewportHeight /2f, 0);
    camera.setToOrtho(false, 800, 480);
    maploader = new TmxMapLoader();
    map = maploader.load("mapa_feio.tmx");
    renderer = new OrthogonalTiledMapRenderer(map);
    camera.update();
  }

  @Override
  public void render (float delta) {
    ScreenUtils.clear(1,1,0, 1);
    renderer.setView(camera);
    renderer.render();
    megamano.interagir();
    if (megamano.hitbox.x >=  centro  ){
      camera.position.set((int)(camera.position.x += megamano.VELOCIDADE), camera.position.y, 0);
      centro += megamano.VELOCIDADE;
    }
    if (megamano.hitbox.x <=  centro - megamano.megamanSprite.getRegionWidth() && camera.position.x > 400 ){
      camera.position.set((int)(camera.position.x -= megamano.VELOCIDADE), camera.position.y, 0);
      centro -= megamano.VELOCIDADE;
    }
    if (megamano.hitbox.x < 0)
    megamano.hitbox.x = 0;
    camera.update();
    game.batch.setProjectionMatrix(camera.combined);
    game.batch.begin();
    megamano.renderizar(game.batch);
    game.batch.end();
    game.batch2.begin();
    game.texto.draw(game.batch2, "X: " + megamano.hitbox.x, 0, 480);
    game.texto.draw(game.batch2, "Y: " + megamano.hitbox.y, 0, 460);
    game.batch2.end();
  }

  @Override
  public void dispose () {
    megamano.dispose();
  }

  @Override
  public void resize (int x, int y) {
  }
  @Override
  public void pause () {
  }
  @Override
  public void hide () {
  }
  @Override
  public void show () {
  }
  @Override
  public void resume () {
  }
}
