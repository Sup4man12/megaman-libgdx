package com.game.megaman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class MegamanCharacter{
  Texture megamanTexture;
  Sprite megamanSprite;
  Rectangle hitbox ;
  int lado = 1;
  int dash = 0;
  private int pulo = 0;
  private int gravidade ;
  private float ULTIMO_TIRO ;
  private boolean ATIRAR = false;
  private boolean CONTROLE_ATIRAR;
  private boolean CONTROLE_DIREITA;
  private boolean CONTROLE_BAIXO;
  private boolean CONTROLE_PULAR;
  private boolean CONTROLE_JUST_PULAR;
  private boolean CONTROLE_ESQUERDA;
  AnimationConstructor megaman_parado = new AnimationConstructor("megaman/megaman_parado.png", 2, 1, 1.180f);
  AnimationConstructor megaman_run = new AnimationConstructor("megaman/megaman_run.png", 4, 1, 0.095f);
  AnimationConstructor megaman_run_atirar = new AnimationConstructor("megaman/megaman_run_atirando.png", 4, 1, 0.095f);
  Texture megaman_dash = new Texture(Gdx.files.internal("megaman/megaman_dash.png"));
  Texture megaman_ar = new Texture(Gdx.files.internal("megaman/megaman_ar.png"));
  Texture megaman_ar_atirando = new Texture(Gdx.files.internal("megaman/megaman_ar_atirando.png"));
  Texture megaman_parado_atirar = new Texture(Gdx.files.internal("megaman/megaman_parado_atirar.png"));
  float tempo = 0f;
  int VELOCIDADE;
  MegamanCharacter(){
    hitbox = new Rectangle();
    megamanTexture = new Texture(Gdx.files.internal("megaman/megaman.png"));
    megamanSprite = new Sprite(megamanTexture);
    hitbox.x = 0;
    hitbox.y = 300;
    hitbox.setWidth(megamanTexture.getWidth());
    hitbox.setHeight(megamanTexture.getHeight());
  }
  void interagir(){
    gravidade = (int)(370 * Gdx.graphics.getDeltaTime());
    tempo += Gdx.graphics.getDeltaTime();
    controle();
    if (hitbox.y <= 0){
      hitbox.y =0;
      hitbox.y += gravidade;
      if (pulo == 200)
      ATIRAR = false;
      pulo = 0;
    }
    hitbox.y -= gravidade;
    if (lado == -1){
      megamanSprite.setFlip(true, false);
    } else if (lado == 1){
      megamanSprite.setFlip(false, false);
    }
  }
  void renderizar(SpriteBatch spritebatch){
    spritebatch.draw(megamanSprite, hitbox.getX(), hitbox.getY());
    if (ATIRAR == true)
    megamanSprite.setRegion(megaman_parado_atirar);
    else{
      megamanSprite.setRegion(megaman_parado.animation.getKeyFrame(tempo, true));}
    if (!(hitbox.y <= 0))
    if(ATIRAR == true)
    megamanSprite.setRegion(megaman_ar_atirando);
    else
    megamanSprite.setRegion(megaman_ar);
  }
  private void pular(){
    hitbox.y+=(int)(gravidade * 2);
    pulo+=10;
  }
  private void controle(){
    CONTROLE_DIREITA = Gdx.input.isKeyPressed(Keys.RIGHT);
    CONTROLE_ATIRAR = Gdx.input.isKeyJustPressed(Keys.X);
    CONTROLE_ESQUERDA = Gdx.input.isKeyPressed(Keys.LEFT);
    CONTROLE_BAIXO = Gdx.input.isKeyPressed(Keys.DOWN);
    CONTROLE_PULAR = Gdx.input.isKeyPressed(Keys.Z);
    CONTROLE_JUST_PULAR = Gdx.input.isKeyJustPressed(Keys.Z);
    VELOCIDADE = (int)(200 * Gdx.graphics.getDeltaTime());
    if (CONTROLE_DIREITA && CONTROLE_ESQUERDA){
      lado = 1;
    }
    // --SET-LADO--
    else if (CONTROLE_ESQUERDA){
      if(lado == 1 && dash > 0)
      dash = 600;
      lado = -1;
    }
    else if (CONTROLE_DIREITA){
      if(lado == -1 && dash > 0)
      dash = 600;
      lado = 1;
    }
    if (CONTROLE_ATIRAR && dash == 0){
      ATIRAR = true;
      ULTIMO_TIRO = TimeUtils.nanoTime();
    }if (TimeUtils.nanoTime() - ULTIMO_TIRO >= 350000000 || dash > 0){
      ATIRAR = false;
    }
    //  --DASH--
    if (((CONTROLE_BAIXO && CONTROLE_JUST_PULAR) ||
    dash > 0 && dash < 300)  && hitbox.y <=0  ){
      if ( lado != 0)
      megamanSprite.setRegion(megaman_dash);
      if (dash < 300){
        VELOCIDADE = VELOCIDADE * 2;
        hitbox.x+=(VELOCIDADE)*lado;
        dash+=20;} 
    }else{ 
      dash = 0;
    }
    //  --ANDAR--
    if ((CONTROLE_DIREITA || CONTROLE_ESQUERDA) && dash == 0){
      hitbox.x+=(VELOCIDADE)*lado;
      if (pulo == 0 && !(lado == 0))
      if (hitbox.y <= 0){
        if (ATIRAR == true)
        megamanSprite.setRegion(megaman_run_atirar.animation.getKeyFrame(tempo, true));
        else
        megamanSprite.setRegion(megaman_run.animation.getKeyFrame(tempo, true));}
    }
    // --PULAR--
    if (CONTROLE_JUST_PULAR && pulo == 0 && !CONTROLE_BAIXO && (hitbox.y <=0)){
      pular();
      ATIRAR = false;
    }
    if (CONTROLE_PULAR && pulo > 0 && pulo < 150 && !CONTROLE_BAIXO){
      pular();
    } else if(pulo>0) {
      pulo = 200;
    }
  }
  void dispose(){
    megamanTexture.dispose();
    megaman_ar.dispose();
    megaman_parado_atirar.dispose();
    megaman_dash.dispose();
    megaman_parado.sprite_sheet.dispose();
    megaman_run.sprite_sheet.dispose();
  }
}
