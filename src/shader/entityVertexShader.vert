#version 400

in vec2 position ;
in vec2 textureCoords ;

uniform mat4 projectionMatrix ;
uniform mat4 viewMatrix ;
uniform mat4 transformationMatrix ;
uniform float worldPosition ;
out vec2 out_textureCoords ;

uniform float numberOfRows ;
uniform float numberOfColumns ;
uniform vec2 textureOffset ;

void main(void) {

	gl_Position = projectionMatrix *viewMatrix*transformationMatrix* vec4(position,worldPosition, 1.0);

	out_textureCoords = textureCoords / vec2(numberOfRows, numberOfColumns) + textureOffset;
}
