package utils;

import java.nio.FloatBuffer;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;

public class Matrix4 {
	
	public float m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33;
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(m00).append(' ').append(m10).append(' ').append(m20).append(' ').append(m30).append('\n');
		buf.append(m01).append(' ').append(m11).append(' ').append(m21).append(' ').append(m31).append('\n');
		buf.append(m02).append(' ').append(m12).append(' ').append(m22).append(' ').append(m32).append('\n');
		buf.append(m03).append(' ').append(m13).append(' ').append(m23).append(' ').append(m33).append('\n');
		return buf.toString();
	}
	public Matrix4 setIdentity() {
		return setIdentity(this);
	}
	public static Matrix4 createTransformationMatrix(Vec2 position , float rotation, Vec2 scale) {
		Matrix4 matrix = new Matrix4();
		matrix.setIdentity() ;
		Matrix4.translate(position, matrix, matrix);
		Matrix4.rotate(rotation, matrix) ;
		if(scale.x < 0 ) {
			scale.x = 0 ;
		}
		if(scale.y < 0) {
			scale.y = 0 ;
		}
		Matrix4.scale(scale, matrix, matrix) ;
		
		return matrix ;
		
	}
	public static Matrix4 updateTransformationMatrix(Matrix4 matrix , Vec2 position, float rotation , Vec2 scale) {
		matrix.setIdentity() ;
		Matrix4.translate(position, matrix, matrix);
		Matrix4.rotate(rotation, matrix) ;
		if(scale.x < 0 ) {
			scale.x = 0 ;
		}
		if(scale.y < 0) {
			scale.y = 0 ;
		}
		Matrix4.scale(scale, matrix, matrix) ;
		
		return matrix ;
	}
	/*Represented by me*/
	public static Vec2 MatrixMul(Matrix4 m , Vec2 v) {
		float x = m.m00 * v.x ;
		float y = m.m10 * v.y ;
		float w = m.m01 * v.x ;
		float z = m.m11 * v.y ;
		return new Vec2(x+y , w+z) ;
	}
	public static Vec2 MatrixMulPlusPos(Matrix4 m, Vec2 v, Vec2 pos) {
		float x = m.m00 * v.x ;
		float y = m.m10 * v.y ;
		float w = m.m01 * v.x ;
		float z = m.m11 * v.y ;
		return new Vec2(x+y + pos.x , w+z + pos.y) ;
	}
	
	public static void rotate(float radians, Matrix4 matrix) {
		float radian = (float) Math.toRadians(radians) ;
		float c = (float)Math.cos( radian );
		float s = (float)Math.sin( radian );

		matrix.m00 = c;
		matrix.m01 = s;
		matrix.m10 = -s;
		matrix.m11 = c;
	
}
	public static Matrix4 setIdentity(Matrix4 m) {
		m.m00 = 1.0f;
		m.m01 = 0.0f;
		m.m02 = 0.0f;
		m.m03 = 0.0f;
		m.m10 = 0.0f;
		m.m11 = 1.0f;
		m.m12 = 0.0f;
		m.m13 = 0.0f;
		m.m20 = 0.0f;
		m.m21 = 0.0f;
		m.m22 = 1.0f;
		m.m23 = 0.0f;
		m.m30 = 0.0f;
		m.m31 = 0.0f;
		m.m32 = 0.0f;
		m.m33 = 1.0f;

		return m;
	}
	public static Matrix4 load(Matrix4 src, Matrix4 dest) {
		if (dest == null)
			dest = new Matrix4();
		dest.m00 = src.m00;
		dest.m01 = src.m01;
		dest.m02 = src.m02;
		dest.m03 = src.m03;
		dest.m10 = src.m10;
		dest.m11 = src.m11;
		dest.m12 = src.m12;
		dest.m13 = src.m13;
		dest.m20 = src.m20;
		dest.m21 = src.m21;
		dest.m22 = src.m22;
		dest.m23 = src.m23;
		dest.m30 = src.m30;
		dest.m31 = src.m31;
		dest.m32 = src.m32;
		dest.m33 = src.m33;

		return dest;
	}
	public static Matrix4 scale(Vec2 vec, Matrix4 src, Matrix4 dest) {
		if (dest == null)
			dest = new Matrix4();
		dest.m00 = src.m00 * vec.x;
		dest.m01 = src.m01 * vec.x;
		dest.m02 = src.m02 * vec.x;
		dest.m03 = src.m03 * vec.x;
		
		dest.m10 = src.m10 * vec.y;
		dest.m11 = src.m11 * vec.y;
		dest.m12 = src.m12 * vec.y;
		dest.m13 = src.m13 * vec.y;
		
		return dest;
	}
	public Matrix4 store(FloatBuffer buf) {
		buf.put(m00);
		buf.put(m01);
		buf.put(m02);
		buf.put(m03);
		buf.put(m10);
		buf.put(m11);
		buf.put(m12);
		buf.put(m13);
		buf.put(m20);
		buf.put(m21);
		buf.put(m22);
		buf.put(m23);
		buf.put(m30);
		buf.put(m31);
		buf.put(m32);
		buf.put(m33);
		return this;
	
	}
	public static Vec4 transform(Matrix4 left, Vec4 right, Vec4 dest) {
		if (dest == null)
			dest = new Vec4();

		float x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z + left.m30 * right.w;
		float y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z + left.m31 * right.w;
		float z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z + left.m32 * right.w;
		float w = left.m03 * right.x + left.m13 * right.y + left.m23 * right.z + left.m33 * right.w;

		dest.x = x;
		dest.y = y;
		dest.z = z;
		dest.w = w;

		return dest;
	}

	public static Matrix4 translate(Vec2 vec, Matrix4 src, Matrix4 dest) {
		if (dest == null)
			dest = new Matrix4();

		dest.m30 += src.m00 * vec.x + src.m10 * vec.y;
		dest.m31 += src.m01 * vec.x + src.m11 * vec.y;
		dest.m32 += src.m02 * vec.x + src.m12 * vec.y;
		dest.m33 += src.m03 * vec.x + src.m13 * vec.y;

		return dest;
	}
	public static Matrix4 translate(Vec3 vec, Matrix4 src, Matrix4 dest) {
		if (dest == null)
			dest = new Matrix4();

		dest.m30 += src.m00 * vec.x + src.m10 * vec.y + src.m20 * vec.z;
		dest.m31 += src.m01 * vec.x + src.m11 * vec.y + src.m21 * vec.z;
		dest.m32 += src.m02 * vec.x + src.m12 * vec.y + src.m22 * vec.z;
		dest.m33 += src.m03 * vec.x + src.m13 * vec.y + src.m23 * vec.z;

		return dest;
	}
	public static Matrix4 transpose(Matrix4 src, Matrix4 dest) {
		if (dest == null)
		   dest = new Matrix4();
		float m00 = src.m00;
		float m01 = src.m10;
		float m02 = src.m20;
		float m03 = src.m30;
		float m10 = src.m01;
		float m11 = src.m11;
		float m12 = src.m21;
		float m13 = src.m31;
		float m20 = src.m02;
		float m21 = src.m12;
		float m22 = src.m22;
		float m23 = src.m32;
		float m30 = src.m03;
		float m31 = src.m13;
		float m32 = src.m23;
		float m33 = src.m33;

		dest.m00 = m00;
		dest.m01 = m01;
		dest.m02 = m02;
		dest.m03 = m03;
		dest.m10 = m10;
		dest.m11 = m11;
		dest.m12 = m12;
		dest.m13 = m13;
		dest.m20 = m20;
		dest.m21 = m21;
		dest.m22 = m22;
		dest.m23 = m23;
		dest.m30 = m30;
		dest.m31 = m31;
		dest.m32 = m32;
		dest.m33 = m33;

		return dest;
	}
	public static Matrix4 negate(Matrix4 src, Matrix4 dest) {
		if (dest == null)
			dest = new Matrix4();

		dest.m00 = -src.m00;
		dest.m01 = -src.m01;
		dest.m02 = -src.m02;
		dest.m03 = -src.m03;
		dest.m10 = -src.m10;
		dest.m11 = -src.m11;
		dest.m12 = -src.m12;
		dest.m13 = -src.m13;
		dest.m20 = -src.m20;
		dest.m21 = -src.m21;
		dest.m22 = -src.m22;
		dest.m23 = -src.m23;
		dest.m30 = -src.m30;
		dest.m31 = -src.m31;
		dest.m32 = -src.m32;
		dest.m33 = -src.m33;

		return dest;
	}
	
	
	public static Matrix4 inverse(Matrix4 src, Matrix4 dest) {
		if(dest == null) {
			dest = new Matrix4();
		}
		float Invdeterminant = 1 / determinant(src);
		
		float t00 =  determinant3x3(src.m11, src.m12, src.m13, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
		float t01 = -determinant3x3(src.m10, src.m12, src.m13, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
		float t02 =  determinant3x3(src.m10, src.m11, src.m13, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
		float t03 = -determinant3x3(src.m10, src.m11, src.m12, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
		// second row
		float t10 = -determinant3x3(src.m01, src.m02, src.m03, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
		float t11 =  determinant3x3(src.m00, src.m02, src.m03, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
		float t12 = -determinant3x3(src.m00, src.m01, src.m03, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
		float t13 =  determinant3x3(src.m00, src.m01, src.m02, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
		// third row
		float t20 =  determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m31, src.m32, src.m33);
		float t21 = -determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m30, src.m32, src.m33);
		float t22 =  determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m30, src.m31, src.m33);
		float t23 = -determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m30, src.m31, src.m32);
		// fourth row
		float t30 = -determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m21, src.m22, src.m23);
		float t31 =  determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m20, src.m22, src.m23);
		float t32 = -determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m20, src.m21, src.m23);
		float t33 =  determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22);
		
		dest.m00 = t00*Invdeterminant;
		dest.m11 = t11*Invdeterminant;
		dest.m22 = t22*Invdeterminant;
		dest.m33 = t33*Invdeterminant;
		dest.m01 = t10*Invdeterminant;
		dest.m10 = t01*Invdeterminant;
		dest.m20 = t02*Invdeterminant;
		dest.m02 = t20*Invdeterminant;
		dest.m12 = t21*Invdeterminant;
		dest.m21 = t12*Invdeterminant;
		dest.m03 = t30*Invdeterminant;
		dest.m30 = t03*Invdeterminant;
		dest.m13 = t31*Invdeterminant;
		dest.m31 = t13*Invdeterminant;
		dest.m32 = t23*Invdeterminant;
		dest.m23 = t32*Invdeterminant;
		return dest;
		
	}
	public static float determinant(Matrix4 src) {
		float f =src.m00* ((src.m11 * src.m22 * src.m33 + src.m12 * src.m23 * src.m31 + src.m13 * src.m21 * src.m32)
				- src.m13 * src.m22 * src.m31- src.m11 * src.m23 * src.m32- src.m12 * src.m21 * src.m33)
		-src.m01* ((src.m10 * src.m22 * src.m33 + src.m12 * src.m23 * src.m30 + src.m13 * src.m20 * src.m32)- src.m13 * src.m22 * src.m30- src.m10 * src.m23 * src.m32- src.m12 * src.m20 * src.m33)
		+src.m02* ((src.m10 * src.m21 * src.m33 + src.m11 * src.m23 * src.m30 + src.m13 * src.m20 * src.m31)- src.m13 * src.m21 * src.m30- src.m10 * src.m23 * src.m31- src.m11 * src.m20 * src.m33)
		-src.m03* ((src.m10 * src.m21 * src.m32 + src.m11 *src.m22 * src.m30 + src.m12 * src.m20 * src.m31)- src.m12 * src.m21 * src.m30- src.m10 * src.m22 * src.m31- src.m11 * src.m20 * src.m32);

		return f ;
	}
	
	public static float determinant3x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
		return   m00 * (m11 * m22 - m12 * m21)
			       + m01 * (m12 * m20 - m10 * m22)
			       + m02 * (m10 * m21 - m11 * m20);
	}
}
