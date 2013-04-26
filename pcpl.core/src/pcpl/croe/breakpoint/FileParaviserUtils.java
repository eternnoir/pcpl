package pcpl.croe.breakpoint;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import org.apache.commons.io.IOUtils;


public class FileParaviserUtils {
	  public static ArrayList<IResource> getAllFilesInProject(String type){
		    ArrayList<IResource> allCFiles = new ArrayList<IResource>();
		    IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		    IProject project = FileParaviserUtils.getCurrentProject();

		    IPath path = project.getLocation();

		    recursiveFindFiles(allCFiles,path,myWorkspaceRoot,type);
		    return allCFiles;
		}

		private static void recursiveFindFiles(ArrayList<IResource> allCFiles,IPath path, IWorkspaceRoot myWorkspaceRoot,String type){
		    IContainer  container =  myWorkspaceRoot.getContainerForLocation(path);

		    try {
		        IResource[] iResources;
		        iResources = container.members();
		        for (IResource iR : iResources){
		            // for c files
		            if (type.equalsIgnoreCase(iR.getFileExtension()))
		                allCFiles.add(iR);
		            if (iR.getType() == IResource.FOLDER){
		                IPath tempPath = iR.getLocation();
		                recursiveFindFiles(allCFiles,tempPath,myWorkspaceRoot,type);
		            }
		        }
		    } catch (CoreException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		}

		public static IProject getCurrentProject(){
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			return projects[0];
		}
		
		public static ArrayList<String> getAllFile(String type){
			ArrayList<String> filesString = new ArrayList<String>();
			ArrayList<IResource> resourceList = FileParaviserUtils.getAllFilesInProject(type);
			for(IResource r : resourceList){
				IFile f = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(r.getLocation());
			    try {
					InputStream s = f.getContents();
					String strings = IOUtils.toString(s,"UTF-8");
					filesString.add(strings);
					
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IOException e) {
				}
			}
			/*
			IFile f = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(FileParaviserUtils.get(0).getLocation());
		    String strings;
		    String[] lines;
		    try {
				InputStream s = f.getContents();

				
				strings = IOUtils.toString(s,"UTF-8");
				lines = strings.split(System.getProperty("line.separator"));
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
			}*/
			return filesString;
		}
}