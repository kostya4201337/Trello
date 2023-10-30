import {IBoardEntity} from "../model/entity/IBoardEntity";
import {IBoardCreation} from "../model/dto/IBoardCreation";

export const getBoardsService = (): IBoardEntity[] => {
    return [
        {id: 1, name: 'board1', description: 'boardDesc1', createdAt: new Date(), updatedAt: new Date()},
        {id: 2, name: 'board2', description: 'boardDesc2', createdAt: new Date(), updatedAt: new Date()}
    ];
};

export const addBoardService = (newBoard: IBoardCreation): IBoardEntity => {
    return {id: Date.now(), name: newBoard.name, description: newBoard.description, createdAt: new Date(), updatedAt: new Date()};
}

export const deleteBoardService = (id: number): void => {

};