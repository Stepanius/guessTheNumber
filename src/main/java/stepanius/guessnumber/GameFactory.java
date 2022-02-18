/*
 * Copyright (c) 2022. https://github.com/Stepanius?tab=repositories
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package stepanius.guessnumber;


import stepanius.guessnumber.component.*;
import stepanius.guessnumber.component.config.CommandLineArgumentParser;
import stepanius.guessnumber.component.console.ConsoleDataPrinter;
import stepanius.guessnumber.component.console.ConsoleGameOverHandler;
import stepanius.guessnumber.component.console.ConsoleUserInputReader;
import stepanius.guessnumber.component.swing.GameWindow;
import stepanius.guessnumber.model.config.UserInterface;

import static stepanius.guessnumber.model.config.UserInterface.GUI;

/**
 * @author stepanius
 * @link https://github.com/Stepanius?tab=repositories
 */
public class GameFactory {

    private final UserInterface userInterface;

    public GameFactory(final String[] args) {
        final CommandLineArgumentParser.CommandLineArguments commandLineArguments =
                new CommandLineArgumentParser(args).parse();
        userInterface = commandLineArguments.getUserInterface();
    }

    public Game create() {
        final NumberGenerator numberGenerator = new NumberGenerator();
        final DataPrinter dataPrinter;
        final UserInputReader userInputReader;
        final GameOverHandler gameOverHandler;
        if (userInterface == GUI) {
            final GameWindow gameWindow = new GameWindow();
            dataPrinter = gameWindow;
            userInputReader = gameWindow;
            gameOverHandler = gameWindow;
        } else {
            dataPrinter = new ConsoleDataPrinter();
            userInputReader = new ConsoleUserInputReader(dataPrinter);
            gameOverHandler = new ConsoleGameOverHandler();
        }
        return new Game(numberGenerator, dataPrinter, userInputReader, gameOverHandler);
    }
}
