resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += Resolver.url(
  "sbt-plugin-releases",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/")
)(Resolver.ivyStylePatterns)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.4.0")

addSbtPlugin("play" % "sbt-plugin" % "2.1.1")