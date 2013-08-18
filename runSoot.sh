MYCLASSPATH="/Users/wenzhuman/programming/workspace/ThisFlowAnalysis/bin"
MYCLASSPATH=$MYCLASSPATH":/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/rt.jar"

SOOTCLASSPATH=$MYCLASSPATH":/Users/wenzhuman/programming/workspace/ThisFlowAnalysis/bin"

java -cp "$MYCLASSPATH" RunColor -soot-class-path "$SOOTCLASSPATH" \
-xml-attributes -f J --p jb use-original-names:true  tester.HashSetObject