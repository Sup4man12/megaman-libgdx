package com.game.megaman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationConstructor{
  private int col, row;
  float velocidade;
  Texture sprite_sheet;
  Animation<TextureRegion> animation;
  AnimationConstructor(String textura, int coluna, int linha, float velocidade){
    sprite_sheet = new Texture(Gdx.files.internal(textura));
    col = coluna;
    row = linha;
    TextureRegion tmp[][] = TextureRegion.split(sprite_sheet, sprite_sheet.getWidth()/ coluna, sprite_sheet.getHeight()/linha);
    TextureRegion frame[] = new TextureRegion[col * row];
    int index = 0;
    for (int l = 0; l < row; l ++)
      for (int c = 0; c < col; c++)
        frame[index++] =  tmp[l][c];
    animation = new Animation<TextureRegion>(velocidade, frame);
  }
}
