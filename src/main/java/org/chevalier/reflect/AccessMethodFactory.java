package org.chevalier.reflect;

import org.chevalier.reflect.util.ASMClassLoader;
import org.chevalier.reflect.util.SimpleMap;

/**
 * @author Chevalier (chevalier_zero@hotmail.com)
 */
public final class AccessMethodFactory {
	
	private static SimpleMap<String, AccessMethod> accessSetMethods = new SimpleMap<String, AccessMethod>();
	
	public static AccessMethod getAccessMethod(Class<?> clz) {
		
		AccessMethod accessSetMethod = accessSetMethods.get(clz.getName());
		
		if(accessSetMethod == null){
			
			synchronized (clz.getSimpleName().intern()) {
				
				accessSetMethod = accessSetMethods.get(clz.getName());
				
				if(accessSetMethod == null){
				
					try {
						
						accessSetMethod = ASMClassLoader.getInstance().create(clz);
						accessSetMethods.put(clz.getName(), accessSetMethod);
						
					} catch (Exception e) {}
				}
			}
		}
		
		return accessSetMethod;
	}
}
