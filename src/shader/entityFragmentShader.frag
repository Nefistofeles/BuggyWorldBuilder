#version 400

in vec2 out_textureCoords ;

out vec4 out_Color ;
uniform vec4 color ;
uniform bool useColor ;
uniform sampler2D textureSampler ;

void main(void){

	vec4 colorTest = texture(textureSampler, out_textureCoords) ;
	if(colorTest.a < 0.2){
		discard ;
	}
	if(useColor){
		out_Color = colorTest * color ;
	}else{
		out_Color = colorTest ;
	}

}
