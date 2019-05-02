export interface ITrainingLock {
    id?: number;
    lock?: boolean;
}

export class TrainingLock implements ITrainingLock {
    constructor(public id?: number, public lock?: boolean) {
        this.lock = false;
    }
}
