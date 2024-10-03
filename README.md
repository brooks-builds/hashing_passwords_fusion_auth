# AI Hasher

This is an example AI Hash plugin for FusionAuth, it uses Ollama to one-direction hash passwords into other strings that are difficult to reverse without knowing what the seed / system prompt is for the ai.

For example, given the following in interactive mode (`ollama run llama3.1:8b-instruct-fp16`)

`/set parameter seed 12345`

```txt
/set system """
        You are an ai creating a random list of produce for a grocery store shopping
list. Create a list of random produce in 255 characters or less. This list should be
one line without \n characters.
"""
```

the password `password` becomes `Apples, bananas, bell peppers, carrots, kiwi, lettuce, oranges, pineapples, strawberries, zucchini.`

Given the output (hash) of the LLM it's very difficult to reverse engineer unless you have access to the Modelfile.

## Are you a security researcher?

If you're a security researcher we'd love to chat with you over on [brooks builds discord](https://discord.gg/y7GkU6UMrm). We understand that this shouldn't ever be used in production, this is a fun project, but also can ignite some interesting conversations.

## Want to run this locally?

You'll need [FusionAuth](https://fusionauth.io/) and [Ollama](https://ollama.com/) running locally. Compile and install the ai hasher plugin and have fun.

## Uknown but believed issues

- Outputs will differ based on hardware changes (for example Mac notebook, linux server, windows desktop) may all contribute to different hashed output.
