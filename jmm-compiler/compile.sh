# for f in test/fixtures/libs/java/*; do
#     javac $f
# done

for f in test/jmm/code_gen/*.jmm; do
    java -jar comp2020-5g.jar $f
done
# java -jar jasmin.jar test/fixtures/public/generated_jasmin/*.j