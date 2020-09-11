#version 400

in vec2 out_textureCoords ;

out vec4 out_Color ;

uniform sampler2D textureSampler ;
uniform vec4 color ;

void main(void){

	vec4 finalColor = vec4(color.xyz , texture(textureSampler,out_textureCoords).a * color.a) ;

	if(finalColor.a < 0.1){
		discard ;
	}
	out_Color = finalColor ;

}
