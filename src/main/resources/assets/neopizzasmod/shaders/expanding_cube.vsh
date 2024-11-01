#version 150

in vec3 position;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

void main() {
    // Increase the size of the cube over time
    float scaleFactor = 1.0;
    vec3 expandedPosition = position * scaleFactor;

    // Standard transformation
    gl_Position = ModelViewMat * ProjMat * vec4(expandedPosition, 1.0);
}
