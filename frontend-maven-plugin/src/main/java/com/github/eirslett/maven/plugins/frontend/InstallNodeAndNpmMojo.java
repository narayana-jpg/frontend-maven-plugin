package com.github.eirslett.maven.plugins.frontend;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name="install-node-and-npm", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public final class InstallNodeAndNpmMojo extends AbstractMojo {

    /**
     * The version of Node.js to install. IMPORTANT! Most Node.js version names start with 'v', for example 'v0.10.18'
     */
    @Parameter(required = true)
    private String nodeVersion;

    /**
     * The version of NPM to install.
     */
    @Parameter(required = true)
    private String npmVersion;

    /**
     * The base directory for running all Node commands. (Usually the directory that contains package.json)
     */
    @Parameter(defaultValue = "${basedir}")
    private String targetDir;

    public InstallNodeAndNpmMojo(){

    }

    InstallNodeAndNpmMojo(String nodeVersion, String npmVersion, String targetDir){
        this.nodeVersion = nodeVersion;
        this.npmVersion = npmVersion;
        this.targetDir = targetDir;
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        new NodeAndNPMInstaller(nodeVersion, npmVersion, targetDir, getLog()).install();
    }
}
