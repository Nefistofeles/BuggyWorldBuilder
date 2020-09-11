#version 400

in vec2 out_textureCoords ;

uniform vec4 color ;

out vec4 out_Color ;

void main(void){

	vec4 finalColor = color ;
	if(finalColor.a < 0.2){
		discard;
	}
	out_Color = finalColor ;

}
